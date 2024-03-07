package java21;

// JEP 444: Virtual Threads
// https://openjdk.java.net/jeps/444

// Run with JDK 21
// $ java -Djdk.virtualThreadScheduler.parallelism=2 -Djava.util.concurrent.ForkJoinPool.common.parallelism=2 DemoVirtualThreads.java

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntToLongFunction;

public class DemoVirtualThreads {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .build();

    // Example 1 - Completable Futures
    // HTTP Client with CompletableFuture sendAsync(), will create one thread per request.
    // the default executor is: Executors.newCachedThreadPool()
    public static void testHttpCompletableFuture() throws Exception {
        System.out.println("Active Threads " + Thread.activeCount());
        for (int i = 0; i < 128; ++i) {
            httpClient.sendAsync(HttpRequest.newBuilder()
                    .uri(URI.create("https://hub.dummyapis.com/delay?seconds=3"))
                    .GET()
                    .build(), BodyHandlers.discarding());
            System.out.println("Active Threads " + Thread.activeCount());
        }
        System.out.println(Thread.getAllStackTraces().keySet());
    }

    // CPU Bound Task running on Virtual Threads
    // There is no I/O operation in this method.
    // The Virtual Thread is not able to unmount the task until it is finished.
    // so this task is "blocking" the carrier thread,
    // and the other "pending" virtual threads have to wait.
    private static long cpuBoundTask() {
        final long startTime = System.nanoTime();
        long count = 0;
        while ((System.nanoTime() - startTime) < TimeUnit.SECONDS.toNanos(5)) {
            count++;
        }
        return count;
    }

    // CPU Bound Task with an explicit Yield running on Virtual Threads
    // The yield will tell the Virtual Thread to unmount the task.
    // Other virtual threads can be executed on the carrier thread each yield().
    private static long cpuBoundTaskWithYield() {
        final long startTime = System.nanoTime();
        long count = 0;
        while ((System.nanoTime() - startTime) < TimeUnit.SECONDS.toNanos(5)) {
            if (count++ % 100 == 0) {
                Thread.yield();
            }
        }
        return count;
    }

    // sleep() it's a blocking operation,
    // and it will allow the Carrier Thread to unmount the Virtual Thread
    private static int sleepBoundTask() {
        try {
            // sleep() acts as an I/O operation, allowing the task unmount
            Thread.sleep(5_000);
            return 0;
        } catch (final InterruptedException e) {
            // ignore...
            return -1;
        }
    }

    // I/O Bound Task running on a Virtual Thread
    private static long ioBoundTask() {
        try {
            // some cpu bound code
            long result = 0;
            for (int i = 0; i < 100; ++i) {
                result += i * 3;
            }

            // some blocking (I/O bound) code
            final HttpResponse<Void> resp = httpClient.send(HttpRequest.newBuilder()
                    .uri(URI.create("https://hub.dummyapis.com/delay?seconds=5"))
                    .GET()
                    .build(), BodyHandlers.discarding());

            // more cpu bound code
            return resp.statusCode();
        } catch (final Exception e) {
            // ignore...
            System.err.println("http req failed: " + e.getMessage());
            return -1;
        }
    }

    // synchronized don't play well with Virtual Threads
    // They are blocking the carrier thread.
    // If you are lucky each carrier thread may pick up a task on a different lock giving you some concurrency.
    // if you are not lucky each carrier thread will be blocked waiting for the same lock object.
    private final static Object[] lock = new Object[4];
    static {
        for (int i = 0; i < lock.length; ++i) {
            lock[i] = new Object();
        }
    }
    private static long ioSynchronizedTask(final int taskId) {
        synchronized (lock[taskId % lock.length]) {
            return ioBoundTask();
        }
    }

    // ReentrantLock, ReadWriteLock .lock() is a blocking operation
    // allowing the Carrier Thread to unmount the Virtual Thread
    private static final ReentrantLock[] rlock = new ReentrantLock[4];
    static {
        for (int i = 0; i < rlock.length; ++i) {
            rlock[i] = new ReentrantLock();
        }
    }
    private static long ioLockedTask(final int taskId) {
        final ReentrantLock lock = rlock[taskId % rlock.length];
        lock.lock();
        try {
            return ioBoundTask(); // http.send(), sleep(), ...
        } finally {
            lock.unlock();
        }
    }

    // Semaphore .acquire() is a blocking operation
    // allowing the Carrier Thread to unmount the Virtual Thread
    private static final Semaphore[] smlock = new Semaphore[4];
    static {
        for (int i = 0; i < smlock.length; ++i) {
            smlock[i] = new Semaphore(1);
        }
    }
    private static long ioSmLockedTask(final int taskId) {
        final Semaphore lock = smlock[taskId % rlock.length];
        lock.acquireUninterruptibly();
        try {
            return ioBoundTask();
        } finally {
            lock.release();
        }
    }

    // a demo of multi step task wait blocking operations.
    // each step may run on a different Carrier Thread
    private static long mixTask(final int taskId) {
        try {
            long result = (taskId * 10_000);
            for (int i = 0; i < 10; ++i) {
                System.out.println("task:" + taskId + " op " + i + " -> " + Thread.currentThread());
                Thread.sleep(i * 100);
                result += i * 2;
            }
            System.out.println("task:" + taskId + " done! -> " + Thread.currentThread());
            return result;
        } catch (final Exception e) {
            // ignore, it's just a demo (don't do that in real code)
            return -1;
        }
    }



    private static void runTask(final TaskDef task, final int taskId, final long demoStartTimeNs) {
        final long r = task.runnable.applyAsLong(taskId);
        final long elapsedNs = System.nanoTime() - demoStartTimeNs;
        System.out.println("END of " + task.name() + " id:" + taskId + " took:" + Duration.ofNanos(elapsedNs) + " r:" + r);
    }

    public record TaskDef (String name, IntToLongFunction runnable) {}

    private static final TaskDef[] DEMO_TASKS = {
            new TaskDef("sleepBoundTask", (taskId) -> sleepBoundTask()),
            new TaskDef("ioBoundTask", (taskId) -> ioBoundTask()),
            new TaskDef("cpuBoundTask", (taskId) -> cpuBoundTask()),
            new TaskDef("cpuBoundTaskWithYield", (taskId) -> cpuBoundTaskWithYield()),
            new TaskDef("ioSynchronizedTask", (taskId) -> ioSynchronizedTask(taskId)),
            new TaskDef("ioSmLockedTask", (taskId) -> ioSmLockedTask(taskId)),
            new TaskDef("mixTask", (taskId) -> mixTask(taskId)),
    };

    private static void demoVirtualThreadsExecutor(final TaskDef taskDef) {
        final long startTime = System.nanoTime();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10; ++i) {
                final int taskId = i;
                executor.submit(() -> runTask(taskDef, taskId, startTime));
            }
        }
    }

    private static void demoVirtualThreads(final TaskDef taskDef) throws Exception {
        final long startTime = System.nanoTime();
        final Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; ++i) {
            final int taskId = i;
            threads[i] = Thread.ofVirtual().start(() -> runTask(taskDef, taskId, startTime));
        }
        for (final Thread thread : threads) {
            thread.join();
            final boolean isVirtual = thread.isVirtual();
        }
    }

    // Adjust the common ForkJoinPool concurrency
    //    -Djava.util.concurrent.ForkJoinPool.common.parallelism=2
    // Adjust the VirtualThread forkJoinPool concurrency
    //    -Djdk.virtualThreadScheduler.parallelism=2
    // Show Carrier threads that are blocked (e.g. on a synchronized block)
    //    -Djdk.tracePinnedThreads=full
    public static void main(final String[] args) throws Exception {
        for (final TaskDef taskDef: DEMO_TASKS) {
            System.out.println("--- Demo " + taskDef.name() + " ---");

            System.out.println("VirtualThread ExecutorService demo start for " + taskDef.name());
            demoVirtualThreadsExecutor(taskDef);
            System.out.println("VirtualThread ExecutorService demo done for " + taskDef.name());

            System.out.println("Manual VirtualThread demo start for " + taskDef.name());
            demoVirtualThreads(taskDef);
            System.out.println("Manual VirtualThread demo done for " + taskDef.name());
        }
    }
}

```java
public class JEP405 {
    record Point(int x, int y) {
    }

    static void printSumOld(Object o) {
        if (o instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x + y);
        }
    }

    static void printSumNew(Object o) {
        if (o instanceof Point(int x,int y)) {
            System.out.println(x + y);
        }
    }

    public static void main(String[] args) {
        printSumOld(new Point(22, 20));
        printSumNew(new Point(20, 22));

    }
}

```

```shell
$ java --enable-preview --source 19 JEP405.java
Note: JEP405.java uses preview features of Java SE 19.
Note: Recompile with -Xlint:preview for details.
42
42
```

```java
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.Duration.ofSeconds;

public class JEP425 {

    private static Runnable sleepyHead(AtomicInteger atomicInteger) {
        return () -> {
            try {
                Thread.sleep(ofSeconds(1).toMillis());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Work Done - " + atomicInteger.incrementAndGet());
        };
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger();
        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(sleepyHead(atomicInteger));
            }
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Total elapsed time : " + timeElapsed / 1000.0 + " seconds");
    }
}

```

```text
$ java --enable-preview --source 19 JEP425.java
[...]
Work done - 9996
Work done - 9997
Work done - 9998
Work done - 9999
Work done - 9989
Total elapsed time : 1.471 seconds

```

```java
String foo() throws IOException,InterruptedException {
    int bar=bar(); // kan een IOException of InterruptedException opleveren
    String baz=baz(); //ditto
    return baz+bar;
}

```

```java
import jdk.incubator.concurrent.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class JEP428 {

    String fooSequential() throws InterruptedException, IOException {
        String bar = bar(); // kan een Exception opleveren
        String baz = baz(); //ditto
        return baz + bar;
    }

    String fooStructured() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> bar = scope.fork(this::bar);
            Future<String> baz = scope.fork(this::baz);

            scope.join();
            scope.throwIfFailed();

            return bar.resultNow() + baz.resultNow();
        }
    }

    String fooThreaded() throws ExecutionException, InterruptedException {
        var executorService = new ForkJoinPool(2);
        Future<String> bar = executorService.submit(this::bar);
        Future<String> baz = executorService.submit(this::baz);
        try {
            return bar.get() + baz.get();
        } finally {
            executorService.shutdown();
        }
    }

    String bar() throws InterruptedException {
        Thread.sleep(2000);
        return "bar";
    }

    String baz() throws IOException, InterruptedException {
        Thread.sleep(500);
        return "baz";
//        throw new IOException("baz");
    }

    public static void main(String[] args) {
        var self = new JEP428();
        long start = System.currentTimeMillis();
        try {
            System.out.println(self.fooSequential());
        } catch (InterruptedException | IOException e) {
            System.out.println("Interrupted in sequential");
        }
        long end = System.currentTimeMillis();
        System.out.println("Sequential took " + (end - start) + " ms");

        start = System.currentTimeMillis();
        try {
            System.out.println(self.fooThreaded());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Interrupted in threaded");
        }
        end = System.currentTimeMillis();
        System.out.println("Threaded took " + (end - start) + " ms");

        start = System.currentTimeMillis();
        try {
            System.out.println(self.fooStructured());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Interrupted in structured");
        }
        end = System.currentTimeMillis();
        System.out.println("Structured took " + (end - start) + " ms");
    }
}

```

```shell
$ java --enable-preview --source 19 --add-modules jdk.incubator.concurrent JEP428.java
WARNING: Using incubator modules: jdk.incubator.concurrent
warning: using incubating module(s): jdk.incubator.concurrent
1 warning
bazbar
Sequential took 2504 ms
barbaz
Threaded took 2008 ms
barbaz
Structured took 2062 ms
```

```shell
$ java --enable-preview --source 19 --add-modules jdk.incubator.concurrent JEP428.java
WARNING: Using incubator modules: jdk.incubator.concurrent
warning: using incubating module(s): jdk.incubator.concurrent
1 warning
Interrupted in sequential
Sequential took 2512 ms
Interrupted in threaded
Threaded took 2019 ms
Interrupted in structured
Structured took 531 ms
```

```shell
$ history
export PS1="$ "
java --enable-preview --source 19 JEP425.java
cd ../java17
ls
java --enable-preview --source 19 JEP406.java
cd ../java19
java --enable-preview --source 19 --add-modules jdk.incubator.concurrent JEP428.java
```

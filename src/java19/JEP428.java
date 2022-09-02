package java19;

import jdk.incubator.concurrent.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class JEP428 {

    public static void main(String[] args) {
        var self = new JEP428();
        long start = System.currentTimeMillis();
        try {
            System.out.println(self.fooSequential());
        } catch (InterruptedException | IOException e) {
            System.out.println("Interrupted in sequential");
        }
        System.out.println("Sequential took " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        try {
            System.out.println(self.fooThreaded());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Interrupted in threaded");
        }
        System.out.println("Threaded took " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        try {
            System.out.println(self.fooStructured());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Interrupted in structured");
        }
        System.out.println("Structured took " + (System.currentTimeMillis() - start) + " ms");

    }

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
//        return "baz";
        throw new IOException("baz");
    }
}

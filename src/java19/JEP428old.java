package java19;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class JEP428old {

    String foo() {
        var executorService = new java.util.concurrent.ForkJoinPool(2);
        java.util.concurrent.Future<String> bar = executorService.submit(this::bar);
        java.util.concurrent.Future<String> baz = executorService.submit(this::baz);
        try {
            return bar.get() + baz.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

    String bar() throws InterruptedException {
        Thread.sleep(5000);
        return "bar";
    }

    String baz() {
//        return "baz";
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        var self = new JEP428old();
        System.out.println(self.foo());
    }
}

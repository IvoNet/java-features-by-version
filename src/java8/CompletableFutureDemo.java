import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFutureDemo demo = new CompletableFutureDemo();


        Future<String> completableFuture = demo.calculateAsync();
        final String s = completableFuture.get();
        System.out.println(s);

        CompletableFuture<String> hello
              = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> world = hello
              .thenApply(str -> str + " World");

        System.out.println(world.get());

    }

    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool()
                 .submit(() -> {
                     Thread.sleep(500);
                     completableFuture.complete("Hello");
                     return null;
                 });

        return completableFuture;
    }
}

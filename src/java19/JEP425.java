package java19;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.Duration.ofSeconds;

public class JEP425 {

    private static Runnable workHard(AtomicInteger atomicInteger) {
        return () -> {
            try {
                Thread.sleep(ofSeconds(1).toMillis());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Work done - " + atomicInteger.incrementAndGet());
        };
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger();
        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(workHard(atomicInteger));
            }
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Total elapsed time : " + timeElapsed / 1000.0 + " seconds");
    }
}

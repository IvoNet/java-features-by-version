//package java11;

import java.util.List;
import java.util.function.Predicate;

/**
 * Introduced in Java 1.8
 */
public class PredicateDemo_a {

    public static void main(final String[] args) {
        new PredicateDemo_a().demo();
    }

    private void demo() {
        final List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        final Predicate<Integer> oddNumber = number -> number % 2 == 1;

        numbers.stream()
               .filter(oddNumber.negate())
               .forEach(System.out::println);

    }
}

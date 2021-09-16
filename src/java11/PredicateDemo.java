package java11;

import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(final String[] args) {
        new PredicateDemo().demo();
    }

    private void demo() {
        final List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        numbers.stream()
               .filter(Predicate.not(this::oddNumber))
               .forEach(System.out::println);

    }

    private boolean oddNumber(final Integer number) {
        return number % 2 == 1;
    }
}

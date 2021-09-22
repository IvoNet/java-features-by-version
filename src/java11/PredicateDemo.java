import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(final String[] args) {
        final List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        numbers.stream()
               .filter(Predicate.not(number -> number % 2 == 1))
               .forEach(System.out::println);

    }

}

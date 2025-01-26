import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JEP483 {

    public static void main(String... args) {
        final String fruitsWithA = Stream.of("apple", "banana", "cherry")
                .filter(fruit -> fruit.contains("a"))
                .collect(Collectors.joining(", "));
        System.out.println(fruitsWithA);  // apple, banana
    }

}

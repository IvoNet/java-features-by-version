package java16;

import java.util.List;
import java.util.stream.Collectors;

public class StreamsToListDemo {
    public static void main(final String[] args) {
        final var numbers = List.of("1", "2", "3", "4", "5");
        final var oldWay = numbers.stream()
                                  .map(Integer::valueOf)
                                  .collect(Collectors.toList()); //Collectors.toUnmodifiableList()
        System.out.println(oldWay);

        final var newWay = numbers.stream()
                                  .map(Integer::valueOf)
                                  .toList(); //often used so convenience
        System.out.println(newWay);
    }
}

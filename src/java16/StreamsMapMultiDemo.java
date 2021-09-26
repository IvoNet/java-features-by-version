
import java.util.stream.Stream;

public class StreamsMapMultiDemo {
    public static void main(String[] args) {
        System.out.println("zero-to-one mapping:");
        Stream.of("Java", "Python", "ObjectPascal", "VisualBasic", "Delphi")
              .mapMulti((str, mapper) -> {
                  if (str.length() >= 5)
                      mapper.accept(str.length());  // lengths larger than 5
              })
              .forEach(i -> System.out.print(i + " "));

        System.out.println("\none-to-one mapping:");
        Stream.of("Java", "Python", "ObjectPascal", "VisualBasic", "Delphi")
              .mapMulti((str, mapper) -> mapper.accept(str.length()))
              .forEach(i -> System.out.print(i + " "));

        System.out.println("\none-to-many mapping:");

        Stream.of("Java", "Python", "ObjectPascal", "VisualBasic", "Delphi")
              .mapMulti((str, mapper) -> {
                  for (int i = 0; i < str.length(); i++) {
                      mapper.accept(str.length());
                  }
                  mapper.accept(" ");
              })
              .forEach(System.out::print);

    }
}

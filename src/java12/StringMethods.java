import java.util.Arrays;
import java.util.stream.Collectors;

public class StringMethods {
    public static void main(final String[] args) {
        var str = "Line 1\nLine 2\nLine 3\nLine 4";
        System.out.println(str.indent(5));

        final var transformed = str.transform(s -> Arrays.stream(s.split("is"))
                                                         .map(String::strip)
                                                         .collect(Collectors.joining(": ")));
        System.out.println(transformed);
    }
}

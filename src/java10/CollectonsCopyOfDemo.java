
import java.util.List;

public class CollectonsCopyOfDemo {
    public static void main(String[] args) {
        final List<String> aList = List.of("a", "b", "c");

        var strings = List.copyOf(aList); //returns an UnmodifiableList
        strings.add("foo"); //java.lang.UnsupportedOperationException
    }
}

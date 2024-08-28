//$ java --enable-preview --source 23 JEP476.java
//{A=apple, B=berry, C=citrus}


//Replace the import statements with the import module statement
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import module java.base;
public class JEP476 {
    public static void main(String[] args) {
        String[] fruits = new String[] { "apple", "berry", "citrus" };
        Map<String, String> m = Stream.of(fruits).collect(
                Collectors.toMap(s -> s.toUpperCase().substring(0,1), Function.identity()));
        System.out.println(m);
    }
}

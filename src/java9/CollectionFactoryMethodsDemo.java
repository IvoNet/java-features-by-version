package java9;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionFactoryMethodsDemo {
    public static void main(final String[] args) {
        final Map<String, String> oldWay = Collections.unmodifiableMap(new HashMap<>() {{
            put("key1", "value1");
            put("key2", "value2");
        }});
        System.out.println(oldWay);

        final var newWay = Map.of("key1", "value1", "key2", "value2");
        System.out.println(newWay);

//        Set.of()
//        List.of()
    }
}

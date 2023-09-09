```java
import java.text.MessageFormat;

import static java.lang.System.out;

public class JEP430STR {
    private void stringTemplateExpression() {
        String language = "Java";
        String magazine = "Magazine";
        //OLD - String concatenation
        out.println("Hello, " + language + " " + magazine + "!");
        //OLD - String.formatted
        out.println("Hello, %s %s!".formatted(language, magazine));
        //OLD - MessageFormat
        out.println(MessageFormat.format("Hello, {0} {1}!", language, magazine));
        //NEW - String template
        out.println(STR."Hello, \{language} \{magazine}!");
        //NEW - String template for multiline
        String jsonValue = STR. """
                {
                    "language": "\{language}",
                    "magazine": "\{magazine}"
                }
                """;
        out.println(jsonValue);
    }

    public static void main(String[] args) {
        var jep430 = new JEP430STR();
        jep430.stringTemplateExpression();
    }
}
```

```shell
$ java JEP430STR.java
Note: JEP430STR.java uses preview features of Java SE 21.
Note: Recompile with -Xlint:preview for details.
Hello, Java Magazine!
Hello, Java Magazine!
Hello, Java Magazine!
Hello, Java Magazine!
    {
    "language": "Java",
    "magazine": "Magazine"
}
```

      
=====

```java
import static java.lang.System.out;
import static java.util.FormatProcessor.FMT;

public class JEP430FMT {
    private void objectTemplateExpression() {
        Rectangle[] z = new Rectangle[]{
                new Rectangle("Alfa", 17.8, 31.4),
                new Rectangle("Bravo", 9.6, 12.4),
                new Rectangle("Charlie", 7.1, 11.23),
        };
        String table = FMT. """
                Description    Width   Height  Area
                %-12s\{z[0].name} %7.2f\{z[0].w} %7.2f\{z[0].h} %7.2f\{z[0].area()}
                %-12s\{z[1].name} %7.2f\{z[1].w} %7.2f\{z[1].h} %7.2f\{z[1].area()}
                %-12s\{z[2].name} %7.2f\{z[2].w} %7.2f\{z[2].h} %7.2f\{z[2].area()}
                \{" ".repeat(22)} Total %7.2f\{z[0].area() + z[1].area() + z[2].area()}
                """;
        out.println(table);
    }

    record Rectangle(String name, double w, double h) {
        double area() {
            return w * h;
        }
    }

    public static void main(String[] args) {
        var jep430 = new JEP430FMT();
        jep430.objectTemplateExpression();
    }
}
```

```shell
$ java --enable-preview --source 21 JEP430FMT.java
Note: JEP430FMT.java uses preview features of Java SE 21.
Note: Recompile with -Xlint:preview for details.
Description    Width   Height  Area
Alfa           17.80   31.40  558.92
Bravo           9.60   12.40  119.04
Charlie         7.10   11.23   79.73
                       Total  757.69
```

```java
import org.json.*;

import static java.lang.System.out;

public class JEP430JSON {
    record User(String firstname, String lastname) {
    }

    private User user = new User("Duke", "Java");

    private void template() {
        var JSON = StringTemplate
                .Processor
                .of((StringTemplate template) -> new JSONObject(template.interpolate())
                );
        double tempC = 37.0;

        JSONObject json = JSON. """
                {
                  "user": "\{this.user.firstname()}",
                  "temperatureCelsius": "\{tempC}"
                }
                """;
        json.put("temperatureFahrenheit", (tempC * 9 / 5) + 32);
        out.println(STR."Firstname: \{json.get("this.user")}");
        out.println(STR."Celsius  : \{json.get("temperatureCelsius")}");
        out.println(json.toString(3));
    }

    public static void main(String[] args) {
        var jep430 = new JEP430JSON();
        jep430.template();
    }
}
```

```shell
$ java -cp ./json-20230618.jar --enable-preview --source 21 JEP430JSON.java
Note: JEP430JSON.java uses preview features of Java SE 21.
Note: Recompile with -Xlint:preview for details.
Firstname: Duke
Celsius  : 37.0
{
   "temperatureCelsius": "37.0",
   "user": "Duke",
   "temperatureFahrenheit": 98.6
}
```

```java
return switch (obj) {
    case Integer i when i > 3 -> String.format("int %d", i);
    case Long l when l > 3 -> String.format("long %d", l);
    case String s when s.length() > 3 -> String.format("String %s", s);
    default -> String.format("obj lengt %s", obj.toString().length());
};
```
```shell
+ java --enable-preview --source 21 JEP441.java
```


```java
List<String> items = List.of("first", "second", "third");
//old ugly way
String first = items.get(0); 
//or
String first = items.iterator().next(); 
//and getting the last entry is even worse
String last = items.get(items.size() - 1);
//New in Java 21
String forst = items.getFirst());
String last = items.getLast());
```


```java
List<String> items = List.of("first", "second", "third");
//old ugly way
for (int i = items.size() - 1; i >= 0; i--) {
    System.out.println(items.get(i));
}
//New way
for (String item : items.reversed()) {
    System.out.println(item);
}
//or
items.reversed().forEach(System.out::println);
//and in stream
items.reversed().stream().map(String::toUpperCase).forEach(System.out::println);
```

```java
interface SequencedCollection<E> extends Collection<E> {
    // new method
    SequencedCollection<E> reversed();
    // methods promoted from Deque
    void addFirst(E);
    void addLast(E);
    E getFirst();
    E getLast();
    E removeFirst();
    E removeLast();
}
interface SequencedSet<E> extends SequencedCollection<E>, Set<E> {
    SequencedSet<E> reversed();
}
interface SequencedMap<K,V> extends Map<K,V> {
    // new methods
    SequencedMap<K,V> reversed();
    SequencedSet<K> sequencedKeySet();
    SequencedCollection<V> sequencedValues();
    SequencedSet<Entry<K,V>> sequencedEntrySet();
    V putFirst(K, V);
    V putLast(K, V);
    // methods promoted from NavigableMap
    Entry<K, V> firstEntry();
    Entry<K, V> lastEntry();
    Entry<K, V> pollFirstEntry();
    Entry<K, V> pollLastEntry();
}
```

```shell
$
```


```C++
#include <stdio.h>

int product(int a, int b) {
    return a * b;
}
int add(int a, int b) {
    return a + b;
}
int minus(int a, int b) {
    return a - b;
}

```

```java
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.nio.file.Path;

public class JEP454 {
    public static void main(String[] args) {
        try (var arena = Arena.ofConfined()) {
            var lib = SymbolLookup.libraryLookup(Path.of("calc.so"), arena);
            var linker = Linker.nativeLinker();
            var fd = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT);
            var addFunc = lib.find("add").get();
            var minusFunc = lib.find("minus").get();
            var multiplyFunc = lib.find("product").get();
            System.out.println("sum(20, 22)   = " + linker.downcallHandle(addFunc, fd).invoke(20, 22));
            System.out.println("minus(45, 3)  = " + linker.downcallHandle(minusFunc, fd).invoke(45, 3));
            System.out.println("product(7,6)  = " + linker.downcallHandle(multiplyFunc, fd).invoke(7, 6));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}

```


```java
// a few simple examples
try {
    int number = Integer.parseInt(args[0]);
} catch (NumberFormatException _) { // _ is a valid identifier
    System.out.println("Please enter a valid number");
}

map.computeIfAbsent("key", k -> new ArrayList<>()).add("value");
// becomes
map.computeIfAbsent("key", _ -> new ArrayList<>()).add("value");

```


```java
//Greetings.java
public class Greetings {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
//JEP458.java
public class JEP458 {
    public static void main(String[] args) {
        System.out.println(new Greetings().greet("World"));
    }
}
```

```shell
$ java JEP458.java
Hello, World!
```

```java
switch (ball) {
    case RedBall _   -> process(ball);    // Unnamed pattern variable
    case BlueBall _  -> process(ball);    // Unnamed pattern variable
    case GreenBall _ -> stopProcessing(); // Unnamed pattern variable
}
```

```java
package java20;
import jdk.incubator.concurrent.*;
public class JEP429 {
    private static final ScopedValue<String> USERNAME = ScopedValue.newInstance();
    public static void main(String[] args) {
        ScopedValue.where(USERNAME, "Duke", () -> System.out.println(USERNAME.get()));
        ScopedValue.where(USERNAME, "Ivo", () -> System.out.println(USERNAME.get()));
    }
}
```
```shell
$ java --add-modules jdk.incubator.concurrent --enable-preview --source 20 JEP429.java
WARNING: Using incubator modules: jdk.incubator.concurrent
Duke
Ivo
```
```java
package java20;

public class JEP432 {
    record Pair(Object x, Object y) { }
    record Point(int x, int y) {}
    enum Color { RED, GREEN, BLUE }
    record ColoredPoint(Point p, Color c) {}
    record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) {}

    public static void noMatchExample() {
        Pair p = new Pair(42, 42);
        System.out.println("p instanceof Pair(String s, String t) -> "
                + (p instanceof Pair(String s, String t)));
    }
    static void printUpperLeftColors(Rectangle[] r) {
        for (Rectangle(ColoredPoint(Point p, Color c), ColoredPoint lr): r) {
            System.out.println(c);
        }
    }
    static void dump(Point[] pointArray) {
        // matches all Point instances
        for (Point(var x, var y) : pointArray) {
            System.out.println("(" + x + ", " + y + ")");
        }
    }
    public static void main(String[] args) {
        noMatchExample();
        System.out.println("---");
        printUpperLeftColors(new Rectangle[] {
        new Rectangle(new ColoredPoint(new Point(1, 2), Color.RED),
        new ColoredPoint(new Point(3, 4), Color.BLUE)),
        new Rectangle(new ColoredPoint(new Point(5, 6), Color.GREEN),
        new ColoredPoint(new Point(7, 8), Color.BLUE))
        });
        System.out.println("---");
        dump(new Point[] { new Point(1, 2), new Point(3, 4) });
    }
}


```
```shell
$ java --enable-preview --source 20 JEP432.java
Note: JEP432.java uses preview features of Java SE 20.
Note: Recompile with -Xlint:preview for details.
p instanceof Pair(String s, String t) -> false
---
RED
GREEN
---
(1, 2)
(3, 4)
```
```java
return switch (o) {
    case null -> "Oops";
    case Integer i -> String.format("int %d", i);
    case Long l    -> String.format("long %d", l);
    case Double d  -> String.format("double %f", d);
    case String s  -> String.format("String %s", s);
    default        -> o.toString();
};
```
```java
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
public class JEP434 {
  public static void main(String[] args) throws Throwable {
    // 1. Get a lookup object for commonly used libraries
    SymbolLookup stdlib = Linker.nativeLinker().defaultLookup();
    // 2. Get a handle to the "strlen" function in the C standard library
    MethodHandle strlen = Linker.nativeLinker().downcallHandle(
            stdlib.find("strlen").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS));
    // 3. Convert Java String to C string and store it in off-heap memory
    try (Arena offHeap = Arena.openConfined()) {
        MemorySegment str = offHeap.allocateUtf8String("Java Magazine Rockz!");
        // 4. Invoke the foreign function
        long len = (long) strlen.invoke(str);
        System.out.println("len = " + len);
    }
    // 5. Off-heap memory is deallocated at end of try-with-resources
  }
}
```
```shell
$ javac --enable-preview --source 20 JEP434.java
Note: JEP434.java uses preview features of Java SE 20.
Note: Recompile with -Xlint:preview for details.
$ java --enable-preview --enable-native-access=ALL-UNNAMED JEP434
len = 20
```
      

OpenJDK JEP 432: Record Patterns is an enhancement proposal for the Java programming language that aims to improve the expressiveness and readability of code that deals with records.

A record is a new feature introduced in JDK 14 that allows for the creation of classes that are essentially immutable data containers. They are similar to tuples in other programming languages. A record has a fixed set of components (fields), which are declared in its constructor parameters, and can have methods like any other class.

Record patterns refer to the use of records in a switch statement. Currently, only constant expressions and enum values can be used in switch statements. With record patterns, switch statements can match records based on their components, making it easier to write concise and readable code.

For example, consider the following code:

```java 
public void processVehicle(Vehicle vehicle) {
    switch (vehicle) {
        case Car car:
            processCar(car);
            break;
        case Truck truck:
            processTruck(truck);
            break;
        case Motorcycle bike:
            processMotorcycle(bike);
            break;
        default:
            throw new IllegalArgumentException("Unknown vehicle type!");
    }
}
```

With record patterns, this can be simplified as follows:

```java
public void processVehicle(Vehicle vehicle) {
    switch (vehicle) {
        case Car(String model, int year, Color color):
            processCar(model, year, color);
            break;
        case Truck(String model, int year, int payload):
            processTruck(model, year, payload);
            break;
        case Motorcycle(String model, int year, boolean isElectric):
            processMotorcycle(model, year, isElectric);
            break;
        default:
            throw new IllegalArgumentException("Unknown vehicle type!");
    }
}
```

Here, the switch statement is matching the components of the record and passing them as arguments to the corresponding method. This makes the code more concise and easier to understand.

Overall, record patterns will make working with records in Java more intuitive and improve the readability of code that uses them.

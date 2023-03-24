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

```
```java

```
```java

```

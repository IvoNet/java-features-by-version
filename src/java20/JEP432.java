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

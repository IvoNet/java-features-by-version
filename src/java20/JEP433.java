package java20;
//JEP 433: Pattern Matching for switch (Fourth Preview)


public class JEP433 {
    record Position(int x, int y) {}
    sealed interface Shape permits Rectangle, Circle {} //, Oval
    record Rectangle(Position topLeft, Position bottomRight) implements Shape {}
    record Circle(Position center, int radius) implements Shape {}
//    record Oval(Position center, int width, int height) implements Shape {}
    public static void show(Shape shape) {
        switch (shape) {
            case Rectangle r -> System.out.println(
                    "Rectangle: top left = " + r.topLeft() +
                            "; bottom right = " + r.bottomRight());

            case Circle c -> System.out.println(
                    "Circle: center = " + c.center() +
                            "; radius = " + c.radius());
        }
    }
    public static void main(String[] args) {
        var rectangle = new Rectangle(new Position(10, 10), new Position(50, 50));
        JEP433.show(rectangle);

        var circle = new Circle(new Position(30, 30), 10);
        JEP433.show(circle);
//        var oval = new Oval(new Position(60, 60), 20, 10);
//        JEP433.show(oval);
    }
}

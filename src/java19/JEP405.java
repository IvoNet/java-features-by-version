package java19;

public class JEP405 {
    record Point(int x, int y) {}

    static void printSumOld(Object o) {
        if (o instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x + y);
        }
    }

    static void printSumNew(Object o) {
        if (o instanceof Point(int x,int y)) {
            System.out.println(x + y);
        }
    }

    public static void main(String[] args) {
        printSumOld(new Point(22, 20));
        printSumNew(new Point(20, 22));

    }
}

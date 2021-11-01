import static java.lang.System.out;

public class VarArgsDemo {
    public void varargs(Integer first, String... args) {
        out.println("first = " + first);
        for (final String arg : args) {
            out.println("arg = " + arg);
        }
    }

    public static void main(String... args) {
        VarArgsDemo demo = new VarArgsDemo();
        demo.varargs(42, args);
    }
}

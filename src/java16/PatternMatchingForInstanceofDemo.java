
public class PatternMatchingForInstanceofDemo {
    public static void main(String[] args) {
        Object str = "===Hello, World!===";
        if (str instanceof String s && s.startsWith("===")) { //Assign to 's' and use it immediately
            System.out.println(s.replace("=", "")); //use the assigned 's' as a String in scope.
        }

    }
}

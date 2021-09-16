package java11;

public class StringMethods {

    private static void printBlanks(String str) {
        System.out.println(str.replace(" ", "~"));
    }

    public static void main(String[] args) {
        String str = "    "; // spaces only
        System.out.println(str.isBlank());
        System.out.println(str.isEmpty()); //introduced in 1.6

        // A few other convenient string methods
        printBlanks(" L R ".strip());
        printBlanks(" L R ".stripLeading());
        printBlanks(" L R ".stripTrailing());
        "Line 1\nLine 2\nLine 3\nLine 4".lines().forEach(System.out::println);

    }
}

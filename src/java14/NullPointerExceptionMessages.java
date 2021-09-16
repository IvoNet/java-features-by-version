package java14;

/**
 * Better NullPointerException messages.
 *
 * Pre Java 14:
 * Exception in thread "main" java.lang.NullPointerException
 *         at java14.NPEApp.main(NPEApp.java:6)
 *
 * Java 14+:
 * Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "<local1>[1]" is null
 *         at java14.NPEApp.main(NPEApp.java:6)
 */
public class NullPointerExceptionMessages {
    public static void main(final String[] args) {
        final String[] s = new String[2];
        s[1].length();
    }
}


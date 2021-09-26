
//java NullPointerExceptionMessages.java

/**
 * Introduced in java 14 and standard without the special options needed in 15
 */
public class NullPointerExceptionMessages {
    public static void main(final String[] args) {
        final String[] s = new String[2];
        s[1].length();
    }
}


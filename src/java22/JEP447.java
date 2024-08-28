//$ java --enable-preview --source 22 JEP447.java
//1) Value must be positive
//2) non-positive value
//3) non-positive value

import java.math.BigInteger;

public class JEP447 {

    public static void main(String[] args) {
        // first example shows that we faile late which is not what we want, but we
        // don't have much choice as the super statement must be the first statement
        // in the constructor
        try {
           new PositiveBigInteger(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // second example shows that we can fail early,
        // but we can only do this with an inline static method so that the first statement
        // stays the super statement
        try {
            new PositiveBigInteger2(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // third example is what this JEP tries to solve. It would be nice if we could move the super statement down
        try {
            new PositiveBigInteger3(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}


/**
 * Failing late in the constructor which can lead to some unnecessary work in the super class.
 */
class PositiveBigInteger extends BigInteger {

    PositiveBigInteger(long value) {
        super(String.valueOf(value));
        if (value < 0) {
            throw new IllegalArgumentException("1) Value must be positive");
        }
    }
}

/**
 * Inline static method to enable failing early in the constructor
 */
class PositiveBigInteger2 extends BigInteger {

    public PositiveBigInteger2(long value) {
        super(verifyPositive(value));
    }

    private static String verifyPositive(long value) {
        if (value <= 0)
            throw new IllegalArgumentException("2) non-positive value");
        return String.valueOf(value);
    }

}

/**
 * JEP 447: This is the solution to the problem. We can now move the super statement down.
 */
class PositiveBigInteger3 extends BigInteger {

    public PositiveBigInteger3(long value) {
        if (value <= 0)
            throw new IllegalArgumentException("3) non-positive value");
        super(String.valueOf(value));
    }
}

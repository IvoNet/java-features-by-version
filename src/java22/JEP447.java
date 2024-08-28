//$ java --enable-preview --source 22 JEP447.java
//JEP 447: Statements before super(...) (Preview)
//1) Value must be positive
//2) non-positive value
//3) non-positive value
//Public key cannot be null
//key = [112, 117, 98, 108, 105, 99, 75, 101, 121]

import java.math.BigInteger;
import java.util.Arrays;

public class JEP447 {

    public static void main(String[] args) {
        System.out.println("JEP 447: Statements before super(...) (Preview)");
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

        // Forth Example: a lot more is happening before the super statement
        try {
            new Sub(new Certificate(null));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        new Sub(new Certificate("publicKey"));
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

class Certificate {
    private final String publicKey;

    public Certificate(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }
}

class Super {
    private final byte[] key;

    public Super(byte[] key) {
        this.key = key;
        System.out.println("key = " + Arrays.toString(key));;
    }

    public byte[] getKey() {
        return this.key;
    }
}

class Sub extends Super {
    public Sub(Certificate certificate) {
        var publicKey = certificate.getPublicKey();
        if (publicKey == null) {
            throw new IllegalArgumentException("Public key cannot be null");
        }
        final byte[] key = publicKey.getBytes();
        super(key);
    }
}

package java17;

import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class PseudoRandomGeneratorDemo {
    public static void main(String[] args) {
        RandomGeneratorFactory.all()
                              .map(fac -> fac.group() + ":" + fac.name()
                                    + " {"
                                    + (fac.isSplittable() ? " splitable" : "")
                                    + (fac.isStreamable() ? " streamable" : "")
                                    + (fac.isJumpable() ? " jumpable" : "")
                                    + (fac.isArbitrarilyJumpable() ? " arbitrary-jumpable" : "")
                                    + (fac.isLeapable() ? " leapable" : "")
                                    + (fac.isHardware() ? " hardware" : "")
                                    + (fac.isStatistical() ? " statistical" : "")
                                    + (fac.isStochastic() ? " stochastic" : "")
                                    + " stateBits: " + fac.stateBits()
                                    + " }"
                              )
                              .sorted()
                              .forEach(System.out::println);

        RandomGenerator rng1 = RandomGeneratorFactory.of("Random").create(42);   // new way
        RandomGenerator rng2 = new Random(42);                              // old way
        RandomGenerator rng3 = RandomGeneratorFactory.getDefault().create(42);   // new default
        RandomGenerator rng4 = RandomGenerator.getDefault();                     // shortcut to new default

    }
}

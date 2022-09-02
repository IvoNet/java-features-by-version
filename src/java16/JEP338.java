package java16;

//$ javac --add-modules jdk.incubator.vector JEP338.java
//$ java --add-modules jdk.incubator.vector -XX:+PrintCompilation -Xbatch -XX:-TieredCompilation -XX:CompileThreshold=1 -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly JEP338

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorSpecies;

public class JEP338 {

    void vectorDemo() {

        int a[] = {1, 2, 3, 4};
        int b[] = {1, 2, 3, 4};
        int c[] = new int[a.length];

        VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

        for (int i = 0; i < a.length; i += SPECIES.length()) {

            VectorMask<Integer> m = SPECIES.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(SPECIES, a, i, m);
            IntVector vb = IntVector.fromArray(SPECIES, b, i, m);
            IntVector vc = va.mul(vb);
            vc.intoArray(c, i, m);
        }
    }

    public static void main(String[] args) {
        var self = new JEP338();
        self.vectorDemo();
        self.vectorDemo();
    }

}

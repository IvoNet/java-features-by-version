//$ java --enable-preview --source 24 JEP492.java
//JEP 482: Flexible constructor bodies (Second Preview)
//See java22/JEP447.java for First Preview
//See java23/JEP482.java for Second Preview
//Now we can also initialize fields in the same class before the super
//Sub method. Overridden: IvoNet.nl

public class JEP492 {


    public static void main(String[] args) {
        System.out.println("JEP 482: Flexible constructor bodies (Second Preview)");
        System.out.println("See java22/JEP447.java for First Preview");
        System.out.println("Now we can also initialize fields in the same class before the super");

        // example: the methodToOverride is called in the constructor of Super
        // but the Sub class field was already initialized
        new Sub("IvoNet.nl");
    }
}


class Super {

    public Super() {
        this.methodToOverride();
    }

    void methodToOverride() {
        System.out.println("Super method. Not overridden.");
    }
}

class Sub extends Super {
    private final String name;

    public Sub(String name) {
        this.name = name;
        super();
    }

    @Override
    void methodToOverride() {
        System.out.println("Sub method. Overridden: " + name);
    }
}

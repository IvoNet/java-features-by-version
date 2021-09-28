
public class AnonymousClassesDemo {
    interface Demo {
        String accept(String param);
    }

    public static void main(String[] args) {
        Demo demo = new Demo() {
                @Override
                public String accept(final String param) {
                    return "Hello " + param;
                }
            };
        System.out.println(demo.accept("world."));
    }
}

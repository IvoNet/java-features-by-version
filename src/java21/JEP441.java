package java21;

/**
 * 441:	Pattern Matching for switch
 */
public class JEP441 {

    static void testStringNew(String response) {
        switch (response) {
            case null -> {
            }
            case String s
                    when s.equalsIgnoreCase("YES") -> {
                System.out.println("You got it");
            }
            case String s
                    when s.equalsIgnoreCase("NO") -> {
                System.out.println("Shame");
            }
            case String s -> {
                System.out.println("Sorry?");
            }
        }
    }


    static String patternSwitchTest(Object obj) {
        return switch (obj) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };
    }

    static String patternGuardedCaseLabels(Object obj) {
        return switch (obj) {
            case Integer i when i > 3 -> String.format("int %d", i);
            case Long l when l > 3 -> String.format("long %d", l);
            case String s when s.length() > 3 -> String.format("String %s", s);
            default -> String.format("obj lengt %s", obj.toString().length());
        };
    }

    public static void main(String[] args) {
        JEP441 jep441 = new JEP441();
        testStringNew("YES");
        testStringNew("NO");
        testStringNew("Maybe");
        testStringNew(null);

        System.out.println(patternSwitchTest(42));
        System.out.println(patternSwitchTest(42L));
        System.out.println(patternSwitchTest(42D));
        System.out.println(patternSwitchTest("42"));
        System.out.println(patternSwitchTest(false));

        System.out.println(patternGuardedCaseLabels(42));
        System.out.println(patternGuardedCaseLabels(2));

    }
}

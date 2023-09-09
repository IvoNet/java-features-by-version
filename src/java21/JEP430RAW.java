package java21;


import static java.lang.StringTemplate.RAW;

/**
 * 430:	String Templates (Preview)
 * https://cr.openjdk.org/~jlaskey/templates/docs/api/java.base/java/lang/StringTemplate.html
 */
public class JEP430RAW {

    private void stringRAWTemplateExpression() {
        int x = 10;
        int y = 20;
        String result = STR."\{x} + \{y} = \{x + y}";
        System.out.println(result);
        String name = "Java Magazine";
        java.lang.StringTemplate st = RAW."Hello \{name}. Welcome to \{name}!";
        String info = STR.process(st);
        System.out.println(info);
        System.out.println("st.values() = " + st.values());
        System.out.println("st.fragments() = " + st.fragments());
        result = st.interpolate();
        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        var jep430 = new JEP430RAW();
        jep430.stringRAWTemplateExpression();
    }
}

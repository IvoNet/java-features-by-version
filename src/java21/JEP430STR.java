package java21;


import java.text.MessageFormat;

/**
 * 430:	String Templates (Preview)
 */
public class JEP430STR {

    private void stringTemplateExpression() {
        String language = "Java";
        String magazine = "Magazine";

        //OLD - String concatenation
        System.out.println("Hello, " + language + " " + magazine + "!");
        //OLD - String.formatted
        System.out.println("Hello, %s %s!".formatted(language, magazine));
        //OLD - MessageFormat
        System.out.println(MessageFormat.format("Hello, {0} {1}!", language, magazine));
        //NEW - String template
        System.out.println(STR."Hello, \{language} \{magazine}!");
        //NEW - String template for multiline
        String jsonValue = STR."""
                {
                    "language": "\{language}",
                    "magazine": "\{magazine}"
                }
                """;
        System.out.println(jsonValue);
    }

    public static void main(String[] args) {
        var jep430 = new JEP430STR();
        jep430.stringTemplateExpression();
    }
}

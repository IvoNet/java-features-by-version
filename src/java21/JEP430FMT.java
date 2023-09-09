package java21;

import static java.lang.System.out;
import static java.util.FormatProcessor.FMT;

/**
 * 430:	String Templates (Preview)
 */
public class JEP430FMT {
    private void objectTemplateExpression() {
        Rectangle[] z = new Rectangle[]{
                new Rectangle("Alfa", 17.8, 31.4),
                new Rectangle("Bravo", 9.6, 12.4),
                new Rectangle("Charlie", 7.1, 11.23),
        };
        String table = FMT."""
                Description    Width   Height  Area
                %-12s\{z[0].name} %7.2f\{z[0].w} %7.2f\{z[0].h} %7.2f\{z[0].area()}
                %-12s\{z[1].name} %7.2f\{z[1].w} %7.2f\{z[1].h} %7.2f\{z[1].area()}
                %-12s\{z[2].name} %7.2f\{z[2].w} %7.2f\{z[2].h} %7.2f\{z[2].area()}
                \{" ".repeat(22)} Total %7.2f\{z[0].area() + z[1].area() + z[2].area()}
                """;
        out.println(table);
    }

    record Rectangle(String name, double w, double h) {
        double area() {
            return w * h;
        }
    }

    public static void main(String[] args) {
        var jep430 = new JEP430FMT();
        jep430.objectTemplateExpression();
    }
}

package java10;

import java.util.List;

/**
 * Type Inference.
 * <p>
 * The type of the declared var is inferred from the assignment part of
 * the statement at compile time.
 */
public class TypeInferenceDemo {
    public static void main(final String[] args) {
//        final List<String> languages = List.of("Java", "Python", "Kotlin");
        final var languages = List.of("Java", "Python", "Kotlin");

//        final List<String> otherLanguages = List.of("COBOL", "Perl", "Pascal");
        final var otherLanguages = List.of("COBOL", "Perl", "Pascal");

//      final List<List<String>> combined = List.of(languages, otherLanguages);
        final var combined = List.of(languages, otherLanguages);

        System.out.println("combined = " + combined);
        //combined = [[Java, Python, Kotlin], [COBOL, Perl, Pascal]]

//        Not allowed to assign null to a var.
//        var abc = null;

//        var is NOT a keyword
        var var = "var";
        System.out.println(var);
    }
}

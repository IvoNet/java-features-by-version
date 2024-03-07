import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextBlocks {
    public static void main(final String[] args) {
        final var notThis = "<html>\n" +
              "    <body>\n" +
              "        <p>Hello, world</p>\n" +
              "    </body>\n" +
              "</html>\n";
        System.out.println(notThis);

        final var butThis = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;
        System.out.println(butThis);

        final var query = """
              SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB`
              WHERE `CITY` = 'INDIANAPOLIS'
              ORDER BY `EMP_ID`, `LAST_NAME`;
              """;
        System.out.println(query);

        final var specialStuffLikeTrailingSpacesAndContinuationSign = """
                text with trailing spaces \s
                combined lines from \
                two lines\t
                fiets
                """;
        try {
            Files.write(Paths.get("./specialStuff.txt"), specialStuffLikeTrailingSpacesAndContinuationSign.getBytes());
            System.out.println("Wrote to specialStuff.txt");
            System.out.println("$ cat specialStuff.txt |sed \"s/ /~/g\"|sed \"s/\\t/@/g\"");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//package java15;



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
    }
}

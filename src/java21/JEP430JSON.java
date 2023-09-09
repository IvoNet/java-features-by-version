package java21;


import org.json.*;

/**
 * 430:	String Templates (Preview)
 */
public class JEP430JSON {
    record User(String firstname, String lastname) {
    }


    private User user = new User("Duke", "Java");


    private void template() {
        var JSON = StringTemplate.Processor.of(
                (StringTemplate template) -> new JSONObject(template.interpolate())
        );
        double tempC = 37.0;

        JSONObject json = JSON."""
                {
                  "user": "\{
                    this.user.firstname()
                  }",
                  "temperatureCelsius": "\{tempC}"
                }
                """;

        json.put("temperatureFahrenheit", (tempC * 9 / 5) + 32);

        System.out.println(STR."Firstname: \{json.get("user")}");
        System.out.println(STR."Celsius  : \{json.get("temperatureCelsius")}");
        System.out.println(json.toString(3));
    }

    public static void main(String[] args) {
        var jep430 = new JEP430JSON();
        jep430.template();
    }
}

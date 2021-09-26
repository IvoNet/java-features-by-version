
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatternDemo {
    public static void main(String[] args) {
        // Split URL into protocol, domain, port and URI
        Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher("https://www.ivonet.nl/about");

        matcher.find();

        System.out.println("protocol = " + matcher.group(1));
        System.out.println("domain = " + matcher.group(2));
        System.out.println("port = " + matcher.group(3));
        System.out.println("uri = " + matcher.group(4));
    }
}

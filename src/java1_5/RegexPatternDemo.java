package java1_5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatternDemo {
    public static void main(String[] args) {
        // Split URL into protocol, domain, port and URI
        Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher("https://www.ivonet.nl/about");

        matcher.find();

        String protocol = matcher.group(1);
        String domain   = matcher.group(2);
        String port     = matcher.group(3);
        String uri      = matcher.group(4);
    }
}

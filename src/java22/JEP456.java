package java22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JEP456 {

    public static void main(String[] args) {

        try {
            int number = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.err.println("Not a number");
        }

        try {
            int number = Integer.parseInt(args[0]);
        } catch (NumberFormatException _) { // _ is a valid identifier
            System.out.println("Please enter a valid number");
        }
        Map<String, List<String>> map = new HashMap<>();

        map.computeIfAbsent("key", k -> new ArrayList<>()).add("value");
        // becomes
        map.computeIfAbsent("key", _ -> new ArrayList<>()).add("value");

    }
}

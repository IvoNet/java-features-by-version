package java7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericsDemo {
    public static void main(String[] args) {

        //java 1.5
        Map<String, List<Map<String, Map<String, Integer>>>> things
         = new HashMap<String, List<Map<String, Map<String, Integer>>>>();

        //java 1.7
        Map<String, List<Map<String, Map<String, Integer>>>> things2
         = new HashMap<>();


    }
}

package java21;

import java.util.List;

/**
 * 431:	Sequenced Collections
 */
public class JEP431 {

    void firstLastForList(){
        List<String> items = List.of("first", "second", "third");

        //old ugly way
        String first = items.get(0);
        //or
        String first2 = items.iterator().next();
        //and getting the last entry is even worse
        String last = items.get(items.size() - 1);

        //New way
        System.out.println(items.getFirst());
        System.out.println(items.getLast());
    }

    void reverseForList(){
        List<String> items = List.of("first", "second", "third");
        //old ugly way
        for (int i = items.size() - 1; i >= 0; i--) {
            System.out.println(items.get(i));
        }
        //New way
        for (String item : items.reversed()) {
            System.out.println(item);
        }

        //or
        items.reversed().forEach(System.out::println);

        //and in stream
        items.reversed().stream().map(String::toUpperCase).forEach(System.out::println);
    }

    public static void main(String[] args) {
        JEP431 jep431 = new JEP431();
        jep431.firstLastForList();
        jep431.reverseForList();

    }

}

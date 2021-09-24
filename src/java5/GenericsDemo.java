package java5;

import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {

    public static void main(final String[] args) {

        //old
        final List oldList = new ArrayList();
        oldList.add("test"); // A String that cannot be cast to an Integer
        final Integer i = (Integer)oldList.get(0); // Run time error

        //new
        final List<String> newList = new ArrayList<String>();
        newList.add("test");
        final Integer i = (Integer)newList.get(0); // (type error)  compilation-time error

    }
}

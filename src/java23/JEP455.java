import java.util.Map;

//USAGE:
//openjdk.sh 23
//$ java --enable-preview --source 23 JEP455.java
//Pattern Matching for switch (Second Preview)
//b = 42
//Status is unknown
//j is 0
//j is 1
//Status is unknown: 2
//Status is unknown: 3
//Status is unknown: 4
//Status is unknown: 5
//Status is unknown: 6
//Status is unknown: 7
//Status is unknown: 8
//Status is unknown: 9
//Status is unknown: 10
//Status is between 10 and 40: 11
//Status is between 10 and 40: 12
//Status is between 10 and 40: 13
//Status is between 10 and 40: 14
//Status is between 10 and 40: 15
//Status is between 10 and 40: 16
//Status is between 10 and 40: 17
//Status is between 10 and 40: 18
//Status is between 10 and 40: 19
//Status is between 10 and 40: 20
//Status is between 10 and 40: 21
//Status is between 10 and 40: 22
//Status is between 10 and 40: 23
//Status is between 10 and 40: 24
//Status is between 10 and 40: 25
//Status is between 10 and 40: 26
//Status is between 10 and 40: 27
//Status is between 10 and 40: 28
//Status is between 10 and 40: 29
//Status is between 10 and 40: 30
//Status is between 10 and 40: 31
//Status is between 10 and 40: 32
//Status is between 10 and 40: 33
//Status is between 10 and 40: 34
//Status is between 10 and 40: 35
//Status is between 10 and 40: 36
//Status is between 10 and 40: 37
//Status is between 10 and 40: 38
//Status is between 10 and 40: 39
//Status is unknown: 40
//Status is greater than 40: 41
//Status is greater than 40: 42
//Name: John, Age: 30
//You took the blue pill: BluePill{}

public class JEP455 {

    private static Worker worker = new Worker();

    public static void main(String[] args) {
        System.out.println("Pattern Matching for switch (Second Preview)");
        int i = 42;
        // before pattern matching
        if (i >= -128 && i <= 127) {
            byte b = (byte) i;
        }
        // pattern matching
        if (i instanceof byte b) {
            // use b
            System.out.println("b = " + b);
        }

        worker.setStatus(i);

        //Old way
        switch (worker.getStatus()) {
            case 0 -> System.out.println("Status is 0");
            case 1 -> System.out.println("Status is 1");
            default -> System.out.println("Status is unknown");
        }

        // new way with pattern matching
        for (int j = 0; j < 43; j++) {
            switch (j) {
                case 0 -> System.out.println("j is 0");
                case 1 -> System.out.println("j is 1");
                case int y when y > 10 && y < 40 -> System.out.println("Status is between 10 and 40: " + y);
                case int y when y > 40 -> System.out.println("Status is greater than 40: " + y);
                case int y -> System.out.println("Status is unknown: " + y);
            }
        }

        var json = new JsonValue.JsonObject(
                Map.of(
                        "name", new JsonValue.JsonString("John"),
                        "age", new JsonValue.JsonNumber(30)
                )
        );

        if (json instanceof JsonValue.JsonObject(var map)
                && map.get("name") instanceof JsonValue.JsonString(String n)
                && map.get("age")  instanceof JsonValue.JsonNumber(double a)) {
            int age = (int)a;  // unavoidable (and potentially lossy!) cast
            System.out.println("Name: " + n + ", Age: " + age);
        }

        // Here pattern matching already works as instanceof can be used so boxing will work
        var box = new Box(new BluePill());
        if (box instanceof Box(RedPill rp)) {
            System.out.println("You took the red pill: " + rp);
        } else if (box instanceof Box(BluePill bp)) {
            System.out.println("You took the blue pill: " + bp);
        } else {
            System.out.println("You took no pill");
        }

    }
}

sealed interface JsonValue {
    record JsonString(String s) implements JsonValue {
    }

    record JsonNumber(double d) implements JsonValue {
    }

    record JsonObject(Map<String, JsonValue> map) implements JsonValue {
    }
}

class Worker {
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

record Box(Object obj) {
}

record RedPill(){
    @Override
    public String toString() {
        return "RedPill{}";
    }
}
record BluePill(){
    @Override
    public String toString() {
        return "BluePill{}";
    }
}

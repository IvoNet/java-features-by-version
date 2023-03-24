package java20;
import jdk.incubator.concurrent.*;
public class JEP429 {
    private static final ScopedValue<String> USERNAME = ScopedValue.newInstance();
    public static void main(String[] args) {
        ScopedValue.where(USERNAME, "Duke", () -> System.out.println(USERNAME.get()));
        ScopedValue.where(USERNAME, "Ivo", () -> System.out.println(USERNAME.get()));
    }
}

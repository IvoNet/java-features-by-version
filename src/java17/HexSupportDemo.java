import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class HexSupportDemo {
    public static void main(final String[] args) {
        final HexFormat hex = HexFormat.of();
        System.out.println(hex.formatHex("Hello World".getBytes(StandardCharsets.UTF_8)));

    }
}

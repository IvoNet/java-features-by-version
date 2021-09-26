
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;

public class LineNumberReaderDemo {
    public static void main(String[] args) throws IOException {
        var text = "Line 1\n Line 2\n Line 3";
        LineNumberReader reader = new LineNumberReader(new StringReader(text));
        while (reader.read() != -1) {
        }
        System.out.println("reader = " + reader.getLineNumber());

//        System.out.println("reader = " + reader);
    }
}

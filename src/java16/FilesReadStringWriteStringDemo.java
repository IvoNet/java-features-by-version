
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FilesReadStringWriteStringDemo {
    public static void main(String[] args) throws IOException {

        //Read File content into a string
        final var content = Files.readString(Path.of("./helloworld.c"));
        System.out.println(content);

        //Some changes
        final String s1 = content.lines()
                                 .map(s -> s.transform(s2 -> "   :" + s.stripLeading()))
                                 .collect(Collectors.joining("\n"));
        System.out.println(s1);
        // Write string content into a file.
        Files.writeString(Path.of("./helloworld.txt"), s1);

    }
}

import java.net.InetSocketAddress;
import java.nio.file.Path;
import com.sun.net.httpserver.SimpleFileServer;
import static com.sun.net.httpserver.SimpleFileServer.OutputLevel;


public class MyServer {

    public static void main(String[] args) {
        var server = SimpleFileServer.createFileServer(new InetSocketAddress(8000), Path.of("/src"), OutputLevel.VERBOSE);
        server.start();
        System.out.println( "Started: http://localhost:8000" );
    }
}

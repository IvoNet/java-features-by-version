package java18;

import java.net.InetSocketAddress;
import java.nio.file.Path;
import com.sun.net.httpserver.SimpleFileServer;
import static com.sun.net.httpserver.SimpleFileServer.OutputLevel;

/**
 * A Simple Static WebServer
 * <br/>
 * Snippet referenced in the code:
 * {@snippet class="JEP408SimpleWebServer" region="example"}
 */
public class JEP408SimpleWebServer {

    /**
     * Start the server.
     */
    public static void main(String[] args) {
        // @start region="example"
        var server = SimpleFileServer.createFileServer(
              new InetSocketAddress(8000), // @replace regex="8000" replacement="PORT_HERE"
              Path.of("/src"), // @replace regex="/src" replacement="PATH_HERE"
              OutputLevel.VERBOSE);  // @link regex="OutputLevel" target="SimpleFileServer.OutputLevel"
        server.start();
        // @end
        System.out.println( "Started: http://localhost:8000" );
    }
}




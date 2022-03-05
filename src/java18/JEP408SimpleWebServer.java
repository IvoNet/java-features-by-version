import java.net.InetSocketAddress;
import java.nio.file.Path;
import com.sun.net.httpserver.SimpleFileServer;
import static com.sun.net.httpserver.SimpleFileServer.OutputLevel;

/**
 * A Simple Static WebServer
 *
 * Snippet you write yourself
 *
 * {@snippet :
 *  var server = com.sun.net.httpserver.SimpleFileServer.createFileServer(new InetSocketAddress(8000),
 *               Path.of("/src"), com.sun.net.httpserver.SimpleFileServer.OutputLevel.VERBOSE);
 *  server.start();
 * }
 *
 * Snippet referenced in the code:
 * {@snippet class="JEP408SimpleWebServer" region="example"}
 */
public class JEP408SimpleWebServer {

    public static void main(String[] args) {
        // @start region="example"
        var server = SimpleFileServer.createFileServer(
              new InetSocketAddress(8000), // @replace regex="8000" replacement="PORT_HERE"
              Path.of("/src"), // @replace regex="/src" replacement="PATH_HERE"
              OutputLevel.VERBOSE);  // @link regex="OutputLevel" target="SimpleFileServer.OutputLevel"
        server.start();
        System.out.println( "Started: http://localhost:8000" );
        // @end
    }
}


//To get this example to work for the snippit javadoc
//$ javadoc --snippet-path . -d doc JEP408SimpleWebServer.java
//$ jwebserver -b 0.0.0.0 -p 8000
//open http://localhost:8000/doc

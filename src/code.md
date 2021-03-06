```java
**
 * A Simple Static WebServer
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
        // @end
        System.out.println( "Started: http://localhost:8000" );
    }
}

```



```java
/**
 * A Simple Static WebServer

 * {@snippet :
 *  var server = com.sun.net.httpserver.SimpleFileServer.createFileServer(new InetSocketAddress(8000),
 *               Path.of("/src"), com.sun.net.httpserver.SimpleFileServer.OutputLevel.VERBOSE);
 *  server.start();
 * }
 */
```

```shell
$ javadoc --snippet-path . -d ./doc JEP408SimpleWebServer.java 
$ jwebserver -b 0.0.0.0 -p 8000
```


```java
import java.net.InetSocketAddress;
import java.nio.file.Path;
import com.sun.net.httpserver.SimpleFileServer;
import static com.sun.net.httpserver.SimpleFileServer.OutputLevel;

public class JEP408SimpleWebServer {

    public static void main(String[] args) {
        //@start region="example"
        var server = SimpleFileServer.createFileServer(new InetSocketAddress(8000), Path.of("/src"), OutputLevel.VERBOSE);
        server.start();
        System.out.println( "Started: http://localhost:8000" );
        //@end
    }
}
```


```shell
$ java JEP408SimpleWebServer.java
Started: http://localhost:8000
172.17.0.1 - - [05/Mar/2022:17:56:13 +0000] "GET / HTTP/1.1" 200 -
```


```java
//old
List oldList=new ArrayList();
      oldList.add("test"); // A String that cannot be cast to an Integer
      Integer i=(Integer)oldList.get(0); // Run time error

//new
      List<String> newList=new ArrayList<String>();
      newList.add("test");
      Integer i=(Integer)newList.get(0); // (type error)  compilation-time error

```

```java
//java 1.5
Map<String, List<Map<String, Map<String, Integer>>>>things
      =new HashMap<String, List<Map<String, Map<String, Integer>>>>();

//java 1.7
      Map<String, List<Map<String, Map<String, Integer>>>>things
      =new HashMap<>();
```

```java
// We write
Integer num2=50;
      int inum=num2;

//what happens behind the scenes (<1.5)
      Integer num2=new Integer(50);
      int inum=num2.intValue();
```

```java
public enum PizzaStatus {
    ORDERED,
    READY,
    DELIVERED;
}
```

```java
class Person {

    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }
}
```

```java
Connection conn=DriverManager.getConnection(
      "jdbc:somejdbcvendor:other data needed by some jdbc vendor",
      "myLogin",
      "myPassword");
      try{
      /* you use the connection here */
      }finally{
      //It's important to close the connection when you are done with it
      try{
      conn.close();
      }catch(Throwable e){ /* Propagate the original exception
                                instead of this one that you want just logged */
      logger.warn("Could not close JDBC Connection",e);
      }
      }
```

```java
// Split URL into protocol, domain, port and URI
Pattern pattern=Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
      Matcher matcher=pattern.matcher("https://www.ivonet.nl/about");

      matcher.find();

      System.out.println("protocol = "+matcher.group(1));
      System.out.println("domain = "+matcher.group(2));
      System.out.println("port = "+matcher.group(3));
      System.out.println("uri = "+matcher.group(4));

```

```java
LocalDate localDate=LocalDate.now();
      System.out.println("localDate = "+localDate);

      System.out.println("LocalDate.of(); = "+LocalDate.of(2021,9,28));
      System.out.println("LocalDate.parse() = "+LocalDate.parse("2021-09-28"));

      System.out.println("Tomorrow = "+LocalDate.now().plusDays(1));

      LocalDate previousMonthSameDay=LocalDate.now().minus(1,ChronoUnit.MONTHS);
      System.out.println("previousMonthSameDay = "+previousMonthSameDay);

      DayOfWeek dow=LocalDate.parse("2016-06-12").getDayOfWeek();
      System.out.println("DayOfWeek = "+dow);
```

```java
converted.addAll(epub.getAuthors()
      .stream()
      .map(Name::new)
      .filter(p->!removeList.is(p.name().toLowerCase()))
      .filter(p->authorsResource.is(p.name()))
      .map(Name::asAuthor)
      .collect(Collectors.toList()));

```

```java

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DigitalVaultServiceServiceClientProperties.class)
public class DigitalVaultServiceClientConfig {

    @Getter
    private final DigitalVaultServiceServiceClientProperties properties;

    @Bean
    public RestTemplate digitalVaultRestTemplate() {
//      ...
    }
```

```java
public interface Vehicle {

    String speedUp();

    String slowDown();

    default String turnAlarmOn() {
        return "Turning the vehicle alarm on.";
    }

    default String turnAlarmOff() {
        return "Turning the vehicle alarm off.";
    }
}
```

```java
public interface Vehicle {

    // regular / default interface methods

    static int getHorsePower(int kwh,
                             int torque) {
        return (kwh * torque) / 4242;
    }
}
```

```java

@FunctionalInterface
public interface FileFilter {

    boolean accept(File pathname);
}
```

```java
public Future<String> calculateAsync()throws InterruptedException{
      CompletableFuture<String> completableFuture=new CompletableFuture<>();

      Executors.newCachedThreadPool().submit(()->{
      Thread.sleep(500);
      completableFuture.complete("Hello");
      return null;
      });

      return completableFuture;
      }
```

```java
CompletableFuture<String> cf1
      =CompletableFuture.supplyAsync(()->"Hello");

      CompletableFuture<String> cf2=cf1
      .thenApply(s->s+" World");

      assertEquals("Hello World",cf2.get());

```

```java
//final List<String> languages = List.of("Java", "Python", "Kotlin");
final var languages=List.of("Java","Python","Kotlin");

//final List<String> otherLanguages = List.of("COBOL", "Perl", "Pascal");
final var otherLanguages=List.of("COBOL","Perl","Pascal");

//final List<List<String>> combined = List.of(languages, otherLanguages);
final var combined=List.of(languages,otherLanguages);

```

```java
final List<String> aList=List.of("a","b","c");

      var strings=List.copyOf(aList); //returns an unmodifiable List
      strings.add("foo"); //java.lang.UnsupportedOperationException 
```

```java
#!/usr/local/openjdk-11/bin/java --source 11

public class SheBangDemo {
    public static void main(final String[] args) {
        System.out.println("SheBanged yeah!!");
    }
}

```

```java
final List<Integer> numbers=List.of(1,2,3,4,5);

      numbers.stream()
      .filter(Predicate.not(number->number%2==1))
      .forEach(System.out::println);
```

```java
String str="    "; // spaces only
      str.isBlank(); //true
      " L R ".strip(); //"L R"
      " L R ".stripLeading(); //"L R "
      " L R ".stripTrailing(); //" L R"
      "Line 1\nLine 2\nLine 3\nLine 4".lines().forEach(System.out::println);

      System.out.println("Hurray ".repeat(3));

// unicode 10, e.g.
      System.out.println("\u20BF");
```

```java
var client=HttpClient.newBuilder().build();
      var request=HttpRequest.newBuilder()
      .GET()
      .uri(URI.create("https://www.ivowoltring.org"))
      .build();

      var response=client.send(request,HttpResponse.BodyHandlers.ofString());
      System.out.println(response.body());
```

```shell
$ java NullPointerExceptionMessages.java
Exception in thread "main" java.lang.NullPointerException
	at NullPointerExceptionMessages.main(NullPointerExceptionMessages.java:19)
	
$ java -XX:+ShowCodeDetailsInExceptionMessages NullPointerExceptionMessages.java
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" 
    because "<local1>[1]" is null
	at NullPointerExceptionMessages.main(NullPointerExceptionMessages.java:19)

```

```java
public String day(final int day){
      return switch(day){ //switch as an expression
      case 1,2,3,4,5->"weekday";
      case 6,7->"weekend";
default ->"unknown";
      };
      }
```

```java
final var notThis="<html>\n"+
      "    <body>\n"+
      "        <p>Hello, world</p>\n"+
      "    </body>\n"+
      "</html>\n";

final var butThis="""
      <html>
          <body>
              <p>Hello, world</p>
          </body>
      </html>
      """;
```

```java
Object str="===Hello, World!===";
//Assign to 's' and use it immediately
      if(str instanceof String s&&s.startsWith("===")){
      //use the assigned 's' as a String in scope.
      System.out.println(s.replace("=",""));
      }
```

```java
/**
 * Simple record
 */
record Person(String name, String twitterHandle) {
}
```

```java
final var numbers=List.of("1","2","3","4","5");
final var oldWay=numbers.stream()
      .map(Integer::valueOf)
      .collect(Collectors.toList()); //Collectors.toUnmodifiableList()

final var newWay=numbers.stream()
      .map(Integer::valueOf)
      .toList(); //often used so convenience
```

```java
//Read File content into a string
var content=Files.readString(Path.of("./helloworld.c"));
//Some changes

//Write content to file
      Files.writeString(Path.of("./helloworld.cpp"),content);
```

```java
public sealed class Fruit permits Orange,
                                  Apple {
}

public non-sealed class Apple extends Fruit {
}

public non-sealed class Orange extends Fruit {
}

public non-sealed class Tomato extends Fruit {
}

/*
javac Fruit.java Apple.java Orange.java Tomato.java
Tomato.java:3: error: class is not allowed to extend sealed 
class: Fruit (as it is not listed in its permits clause)
public non-sealed class Tomato extends Fruit {}
                  ^
1 error
*/

```

```java
RandomGenerator rng1=RandomGeneratorFactory.of("Random").create(42); // new way
      RandomGenerator rng2=new Random(42); // old way
      RandomGenerator rng3=RandomGeneratorFactory.getDefault().create(42); // new default
      RandomGenerator rng4=RandomGenerator.getDefault(); // shortcut to new default

```

```java
public class AnonymousClassesDemo {
    interface Demo {
        String accept(String param);
    }

    public static void main(String[] args) {
        Demo demo = new Demo() {
            @Override
            public String accept(final String param) {
                return "Hello " + param;
            }
        };
        System.out.println(demo.accept("world."));
    }
}
```

```java
public class InnerClassDemo {
    public static void main(String[] args) {
        OuterClass myOuter = new OuterClass();
        OuterClass.InnerClass myInner = myOuter.new InnerClass();
        System.out.println(myInner.y + myOuter.x);
    }
}

class OuterClass {
    int x = 10;

    class InnerClass {
        int y = 5;
    }
}
```

```java
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterPeriodOfDayDemo {
    public static void main(String[] args) {
        System.out.println(DateTimeFormatter.ofPattern("B")
                                            .format(LocalTime.now()));
    }
}
//in the afternoon
```

```java
import static java.lang.System.out;  //Static imports

public class VarArgsDemo {
    public void varargs(Integer first,
                        String... args) { // String... as vararg
        out.println("first = " + first);
        for (final String arg : args) { //for each enhanced for loop
            out.println("arg = " + arg);
        }
    }

    public static void main(String... args) { //String... as vararg
        VarArgsDemo demo = new VarArgsDemo();
        demo.varargs(42, args);
    }
}
```

```java
import javax.swing.*;
public class JPackageDemo {
    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Hello, world!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 100);
        final JLabel label = new JLabel("Hello, JFall 2021");
        frame.getContentPane().add(label);
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(JPackageDemo::createAndShowGUI);
    }
}
```

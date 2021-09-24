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
//We write
Integer number=100;

//what happens behind the scenes (<1.5)
      Integer number=Integer.valueOf(100);

// We write
      Integer num2=new Integer(50);
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

  @Getter private final DigitalVaultServiceServiceClientProperties properties;

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
    
    static int getHorsePower(int kwh, int torque) {
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
public Future<String> calculateAsync() throws InterruptedException {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    Executors.newCachedThreadPool().submit(() -> {
        Thread.sleep(500);
        completableFuture.complete("Hello");
        return null;
    });

    return completableFuture;
}
```



```java
CompletableFuture<String> cf1
  = CompletableFuture.supplyAsync(() -> "Hello");

CompletableFuture<String> cf2 = cf1
  .thenApply(s -> s + " World");

assertEquals("Hello World", cf2.get());

```

# JEP 413: Code Snippets in Java API Documentation

See code of `JEP408SimpleWebServer`

- To get this example to work for the snippet javadoc

```shell
$ javadoc --snippet-path . -d doc JEP408SimpleWebServer.java
$ jwebserver -b 0.0.0.0 -p 8000
```

- open http://localhost:8888/doc (note 8888 as from host to container it is 8888:8000)


![](JEP413.png)

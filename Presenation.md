# Java 11 to 21

## From a developer perspective

Ivo Woltring
Principal Expert & Codesmith
Editor Java Magazine

---

# Java 11

## ## Local variable type inference

Java SE 10 makes implicit typing available for local variables:

```java
var x = new Foo();
for (var x : xs) { ... }
try (var x = ...) { ... } catch ...
```


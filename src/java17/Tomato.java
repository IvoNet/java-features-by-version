//package java17;

public non-sealed class Tomato extends Fruit {
}


/*
javac Fruit.java Apple.java Orange.java Tomato.java
Tomato.java:3: error: class is not allowed to extend sealed class: Fruit (as it is not listed in its permits clause)
public non-sealed class Tomato extends Fruit {
                  ^
1 error
 */

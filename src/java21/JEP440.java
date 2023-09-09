package java21;

/**
 * 440:	Record Patterns
 */
public class JEP440 {

    record User(String firstname, String lastname) { }
    record Person(String firstname, String lastname) { }

    record Someone(User user, Person person) { }

    record MyPair<S,T>(S fst, T snd){}

    private String concatName(Object thing) {
        if (thing instanceof User u) {
            //this pattern matching exists since Java 16 and this is the simplest form by direct assignment
            return u.firstname() + " " + u.lastname();
        }
        if (thing instanceof Person p) {
            return p.firstname() + " " + p.lastname();
        }
        if (thing instanceof Someone(User user, Person person)) {

            return user.firstname() + " " + person.lastname();
        }
        throw new IllegalArgumentException("Not a User, Person, or Someone");
    }

    private String concatNameJava21(Object thing) {
        if (thing instanceof User(String fn, String ln) ) {
            //note that fn and ln are directly assigned from the record in the instanceof statement from the User record
            return fn + " " + ln;
        }
        if (thing instanceof Person(String fn, String ln)) {
            return fn + " " + ln;
        }
        if (thing instanceof Someone(User(String fn, String ln), Person(String fn2, String ln2))) {
            // the surname from the first User Record in the Someone record is assigned to ln and the firstname
            // of the second Person in the Someone record is assigned to fn2. Nested assignment
            return fn + " " + ln2;
        }
        if (thing instanceof MyPair(String a, String b)) {
            //MyPair is generified
            return a + " " + b;
        }
        if (thing instanceof MyPair(var a, var b)) {
            //MyPair is generified so matching on var will match any type for a and b
            return a + " " + b;
        }

        throw new IllegalArgumentException("Not a User, Person, or Someone");
    }
    public static void main(String[] args) {
        var jep440 = new JEP440();
        System.out.println(jep440.concatName(new User("A", "B")));
        System.out.println(jep440.concatName(new Person("C", "D")));
        System.out.println(jep440.concatName(new Someone(new User("A", "B"), new Person("C", "D"))));
        try {
            System.out.println(jep440.concatName(new Object()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(jep440.concatNameJava21(new User("A", "B")));
        System.out.println(jep440.concatNameJava21(new Person("C", "D")));
        System.out.println(jep440.concatNameJava21(new Someone(new User("A", "B"), new Person("C", "D"))));
        System.out.println(jep440.concatNameJava21(new MyPair<>("mpA", "mpB")));
        System.out.println(jep440.concatNameJava21(new MyPair<>(new User("a", "b"), "mpB")));
        try {
            System.out.println(jep440.concatNameJava21(new Object()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

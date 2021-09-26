

class Person {
    private String name;
    private String twitter;

    public Person(final String name,
                  final String twitter) {
        this.name = name;
        this.twitter = twitter;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        final Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return twitter != null ? twitter.equals(person.twitter) : person.twitter == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (twitter != null ? twitter.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(final String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "Person[" +
              "name=" + name +
              ", twitter=" + twitter  +
              "]";
    }
}

/**
 * Simple record
 */
record APerson(String name, String twitter) {}

/**
 * A record is shallowly immutable.
 *
 * Shallowly immutable means, that if a class has fields, these fields are treated as being final.
 * However, their fields (i.e. the fields of the fields) don't need to be final.
 */
record BPerson(String title, Person classicPerson) {}


record CPerson(String name, String twitter) {

    /**
     * 'Compact' constructor.
     * By removing the parameters you can just do your logic and the initialisation will still happen
     * Compact constructors are only allowed in records.
     */
    public CPerson {
        if (name == null) {
            throw new IllegalArgumentException("The name is mandatory");
        }
    }
}

public class RecordsDemo {

    public static void main(final String[] args) {
        final var person = new Person("Ivo", "@ivonet");
        System.out.println(person);

        final var aPerson = new APerson("Ivo", "@ivonet");
        System.out.println(aPerson);
        System.out.println("aPerson.name() = " + aPerson.name()); //accessor methods represent the name of the fields

        final var bPerson = new BPerson("Shallow", person);
        bPerson.classicPerson().setName("Other"); //fields of fields do not have to be immutable
        System.out.println(bPerson);

        final var cPerson = new CPerson(null, "ivonet");

    }

}

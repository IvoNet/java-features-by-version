
class Address {
    private String street;
    private Integer number;

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
              "street='" + street + '\'' +
              ", number=" + number +
              '}';
    }
}

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

    @Override
    public String toString() {
        return "Person{" +
              "name='" + name + '\'' +
              ", address=" + address +
              '}';
    }
}

public class JavaBeanDemo {

    public static void main(String[] args) {
        Person person = new Person();
        Address address = new Address();
        address.setStreet("MyStreet");
        address.setNumber(10);
        person.setName("Ivo");
        person.setAddress(address);

        System.out.println(person);
    }
}

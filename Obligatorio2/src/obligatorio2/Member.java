package obligatorio2;

/*
 * @author Marco Fiorito and Felipe Najson
 */
public class Member extends Person implements Comparable {

    private String address;

    //Constructor
    public Member(String name, int age, int dni, String address) {
        this.setName(name);
        this.setAge(age);
        this.setDni(dni);
        this.address = address;
        Person.PersonContact.put(dni, Long.MIN_VALUE);
    }

    //Setter Methdos
    public void setAddress(String address) {
        this.address = address;
    }

    //Getter Methods
    public String getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSu dirección es: " + this.getAddress() + "\n";
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareToIgnoreCase(((Member) o).getName());
    }
}

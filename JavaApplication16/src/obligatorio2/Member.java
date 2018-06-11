package obligatorio2;

/*
 * @author Marco Fiorito and Felipe Najson
 */
public class Member extends Person{

    private int age;
    private String address;
    private long contactNumber;

    //Constructor
    public Member(String name, int age, int dni, String adress, long contactNumber) {
        this.setName(name);
        this.age = age;
        this.setDni(dni);
        this.address = adress;
        this.contactNumber = contactNumber;
    }

    //Setter Methdos

    public void setAge(int age) {
        this.age = age;
    }
    public void setAdress(String adress) {
        this.address = adress;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    //Getter Methdos
    public int getAge() {
        return this.age;
    }
    
    public String getAddress() {
        return this.address;
    }

    public long getContactNumber() {
        return this.contactNumber;
    }

    @Override

    public String toString() {
        return "\nEl miembro es " + this.getName() + "\nEdad: " + this.getAge() + "\nDNI: " + this.getDni() + "\nAddress: " + this.getAddress() + "\nNumero de contacto: " + this.getContactNumber();
    }
}

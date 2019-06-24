package obligatorio2;

import java.util.HashMap;

/**
 *
 * @author Felipe Najson and Marco Fiorito
 */
public class Person {

    //Variables of instance
    protected String name;
    protected int dni;
    protected int age;
    public static HashMap<Integer, Long> PersonContact = new HashMap<>();

    //Setter Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //Getter Methods
    public String getName() {
        return this.name;
    }

    public int getDni() {
        return this.dni;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "El nombre es: " + this.getName() + "\nSu DNI es: " + this.getDni() + "\nSu edad es: " + this.getAge();
    }

}

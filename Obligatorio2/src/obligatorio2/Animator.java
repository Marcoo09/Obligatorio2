package obligatorio2;

/**
 *
 * @author Marco Fiorito and Felipe Najson
 */
public class Animator extends Person {

    private int yearsOfExperience;

    //Constructor
    public Animator(String name, int age, int dni, int yearsOfExperience) {
        this.setName(name);
        this.setAge(age);
        this.setDni(dni);
        this.yearsOfExperience = yearsOfExperience;
    }

    //Getter Methods
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    //Setter Methods
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return super.toString() + "\nY tiene " + this.getYearsOfExperience() + " año de experiencia\n";
    }
}

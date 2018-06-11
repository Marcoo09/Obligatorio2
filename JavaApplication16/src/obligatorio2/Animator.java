/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio2;

/**
 *
 * @author Marco Fiorito and Felipe Najson
 */
public class Animator extends Person {
    int yearsOfExperience;

    //Getter Methods
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    
    //Setter Methos
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
   @Override
    public String toString(){
        return "El nombre del animador es: " + this.getName() + "\nY su DNI es: " + this.getDni() + "\nY tiene " + this.getYearsOfExperience() + " de experiencia";
    }
}

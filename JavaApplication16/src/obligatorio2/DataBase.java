package obligatorio2;

import java.util.*;

/**
 *
 * @author Marco Fiorito and Felipe Najson
 */

public class DataBase {

    //Variables of instance
    private ArrayList<Member> listOfMembers = null;
    private ArrayList<Inscription> listOfInscriptions = null;
    private ArrayList<Activity> listOfActivities = null;
    private ArrayList<Animator> listOfAnimators = null;

    //Constructor
    public DataBase() {
        listOfMembers = new ArrayList<>();
        listOfInscriptions = new ArrayList<>();
        listOfActivities = new ArrayList<>();
        listOfAnimators = new ArrayList<>();
    }

    //Getter Methods
    public ArrayList<Member> getListOfMembers() {
        return listOfMembers;
    }

    public ArrayList<Inscription> getListOfInscriptions() {
        return listOfInscriptions;
    }

    public ArrayList<Activity> getListOfActivities() {
        return listOfActivities;
    }

    public ArrayList<Animator> getListOfAnimators() {
        return listOfAnimators;
    }

    //Setter Methods
    public void setListOfMembers(ArrayList<Member> listOfMembers) {
        this.listOfMembers = listOfMembers;
    }

    public void setListOfInscriptions(ArrayList<Inscription> listOfInscriptions) {
        this.listOfInscriptions = listOfInscriptions;
    }

    public void setListOfActivities(ArrayList<Activity> listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    public void setListOfAnimators(ArrayList<Animator> listOfAnimators) {
        this.listOfAnimators = listOfAnimators;
    }

    //Add Member
    public void addMember(Member aMember) {
        this.getListOfMembers().add(aMember);
    }

    //Add Inscription
    public void addInscription(Inscription anInscription) {
        this.getListOfInscriptions().add(anInscription);
    }

    //Add Activity 
    public void addActivity(Activity anActivity) {
        this.getListOfActivities().add(anActivity);
    }

    //Add Animator
    public void addAnimator(Animator anAnimator) {
        this.getListOfAnimators().add(anAnimator);
    }

    //Sort Activity List
    public void sortActivities() {
        Collections.sort(this.getListOfActivities());
    }

    //Sort Member List
    public void sortMemberByName() {
        Collections.sort(this.getListOfMembers());
    }
}

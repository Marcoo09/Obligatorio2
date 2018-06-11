/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio2;

import java.util.*;
/**
 *
 * @author Marco Fiorito and Felipe Najson
 */

public class DataBase {
    //Variables of instance
    ArrayList <Member> listOfMembers = null;
    ArrayList <Inscription> listOfInscriptions = null;
    ArrayList <Activity> listOfActivities = null;
    ArrayList <Animator> listOfAnimators = null;
    
    public DataBase(){
        listOfMembers = new ArrayList<>();
        listOfInscriptions = new ArrayList<>();
        listOfActivities = new ArrayList<>();
        listOfAnimators = new ArrayList<>();
    }

    public ArrayList<Member> getListOfMembers() {
        return listOfMembers;
    }

    public void setListOfMembers(ArrayList<Member> listOfMembers) {
        this.listOfMembers = listOfMembers;
    }

    public ArrayList<Inscription> getListOfInscriptions() {
        return listOfInscriptions;
    }

    public void setListOfInscriptions(ArrayList<Inscription> listOfInscriptions) {
        this.listOfInscriptions = listOfInscriptions;
    }

    public ArrayList<Activity> getListOfActivities() {
        return listOfActivities;
    }

    public void setListOfActivities(ArrayList<Activity> listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    public ArrayList<Animator> getListOfAnimators() {
        return listOfAnimators;
    }

    public void setListOfAnimators(ArrayList<Animator> listOfAnimators) {
        this.listOfAnimators = listOfAnimators;
    }
    
    
}

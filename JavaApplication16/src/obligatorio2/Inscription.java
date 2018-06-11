package obligatorio2;

/*
 * @author Marco Fiorito and Felipe Najson
 */
public class Inscription {

    //Variables of instance
    private Member member;
    private Activity activity;
    private int pickUpTime;

    //Constructor
    public Inscription(Member member, Activity activity, int pickUpTime) {
        this.member = member;
        this.activity = activity;
        this.pickUpTime = pickUpTime;
    }

    //Setter Methods
    public void setPickUpTime(int pickUpTime) {
        this.pickUpTime = pickUpTime;

    }

    public void setMember(Member aMember) {
        this.member = aMember;
    }

    public void setActivity(Activity aActivity) {
        this.activity = aActivity;
    }

    //Getter Methods
    public int getPickUpTime() {
        return this.pickUpTime;
    }

    public Member getMember() {
        return this.member;
    }

    public Activity getActivity() {
        return this.activity;
    }

    //This method check if the inscription is early
    private boolean isAnEarlyInscription() {
        return (this.getPickUpTime() < (this.getActivity().getStartHour() - 3) || this.getPickUpTime() > (this.getActivity().getStartHour()));
    }

    //This method show the inscriptions in order of the pick up time
    public String showOrderedInscriptions(Inscription inscription2) {
        String listReturned = "\n<--------Las inscripciones son-------->\n";
        if (this.getPickUpTime() <= inscription2.getPickUpTime()) {
            listReturned += this.toString() + inscription2;
        } else {
            listReturned += inscription2 + this.toString();
        }
        return listReturned;
    }

    @Override
    public String toString() {
        String isEarly;
        if (this.isAnEarlyInscription()) {
            isEarly = "Es una inscripción TEMPRANA";
        } else {
            isEarly = "NO es una inscripción TEMPRANA";
        }
        return "\nEl miembro inscrito es: " + this.getMember().getName() + "\nLa actividad es de tipo: " + this.getActivity().getTypeString() + "\nLa hora de pasarlo a buscar es: " + this.getPickUpTime() + "\n" + "(" + isEarly + ")" + "\n";
    }
}

package obligatorio2;

/*
 * @author Marco Fiorito and Felipe Najson
 */
public class Inscription {

    //Variables of instance
    private Member member;
    private Activity activity;
    private int pickUpTime;
    private long contactNumber;

    //Constructor
    public Inscription(Member member, Activity activity, int pickUpTime, long contactNumber) {
        this.member = member;
        this.activity = activity;
        this.pickUpTime = pickUpTime;
        this.contactNumber = contactNumber;
        int memberDni = member.getDni();
        Person.PersonContact.put(memberDni, contactNumber);
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

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
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

    public long getContactNumber() {
        return contactNumber;
    }
}

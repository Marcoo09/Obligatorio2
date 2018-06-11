package obligatorio2;

/*
 * @author Felipe Najson and Marco Fiorito
 */
public class Activity {

    //Variables of instance
    private int day;
    private int typeNumeric;
    private String typeString;
    private int startHour;
    private int maxCap;
    private float cost;
    private Animator animator;
    
    //Constructor of Activity
    public Activity(int day, int typeNumeric, int startHour, int maxCap, float cost) {
        this.day = day;
        this.typeNumeric = typeNumeric;
        this.startHour = startHour;
        this.maxCap = maxCap;
        this.cost = cost;

        switch (this.typeNumeric) {
            case 1:
                this.typeString = "Teatro";
                break;
            case 2:
                this.typeString = "Cine";
                break;
            case 3:
                this.typeString = "Viaje";
                break;
            case 4:
                this.typeString = "Paseo local";
                break;
            case 5:
                this.typeString = "Paseo interdepartamental";
                break;
            case 6:
                this.typeString = "Caminata";
                break;
            case 7:
                this.typeString = "Otro";
                break;
            default:
                System.out.println("OPCTIÓN NO VÁLIDA");
                break;
        }

    }

    //Setter methods
    public void setDay(int day) {
        this.day = day;
    }

    public void setTypeSring(String type) {
        this.typeString = type;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setAnimator(Animator animator) {
        this.animator = animator;
    }
    
    //Getter Methdos
    public int getDay() {
        return this.day;
    }

    public String getTypeString() {
        return this.typeString;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getMaxCap() {
        return this.maxCap;
    }

    public float getCost() {
        return this.cost;
    }

    public Animator getAnimator() {
        return animator;
    }
        
    @Override
    public String toString() {
        return "\nLa actividad es de tipo: " + this.typeString + " y es el día " + this.day + " y la hora de comienzo es: " + this.startHour + "\n";
    }

}

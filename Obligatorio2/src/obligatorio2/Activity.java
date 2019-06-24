package obligatorio2;

/*
 * @author Felipe Najson and Marco Fiorito
 */
public class Activity implements Comparable {

    //Variables of instance
    private int day;
    private int typeNumeric;
    private String typeString;
    private int startHour;
    private int maxCap;
    private int cost;
    private Animator animator;

    public static final String[] OptionsTypes = new String[]{
        "",
        "Teatro",
        "Cine",
        "Viaje",
        "Paseo local",
        "Paseo interdepartamental",
        "Caminata",
        "Otro"
    };

    //Constructor of Activity
    public Activity(int day, int typeNumeric, int startHour, int maxCap, int cost, Animator animator) {
        this.day = day;
        this.typeNumeric = typeNumeric;
        this.startHour = startHour;
        this.maxCap = maxCap;
        this.cost = cost;
        this.animator = animator;

        switch (this.typeNumeric) {
            case 1:
                this.typeString = OptionsTypes[1];
                break;
            case 2:
                this.typeString = OptionsTypes[2];
                break;
            case 3:
                this.typeString = OptionsTypes[3];
                break;
            case 4:
                this.typeString = OptionsTypes[4];
                break;
            case 5:
                this.typeString = OptionsTypes[5];
                break;
            case 6:
                this.typeString = OptionsTypes[6];
                break;
            case 7:
                this.typeString = OptionsTypes[7];
                break;
            default:
                System.out.println("OPCTIÓN NO VÁLIDA");
                break;
        }
    }

    //Empty constructor
    public Activity() {
        this.day = 0;
        this.startHour = 0;
        this.maxCap = 0;
        this.cost = 0;
        this.animator = null;
    }

    //Setter methods
    public void setDay(int day) {
        this.day = day;
    }

    public void setTypeString(String type) {
        this.typeString = type;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setAnimator(Animator animator) {
        this.animator = animator;
    }

    //Getter Methods
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

    public int getCost() {
        return this.cost;
    }

    public Animator getAnimator() {
        return animator;
    }

    public int getTypeNumeric() {
        return typeNumeric;
    }

    @Override
    public String toString() {
        return "La actividad es de tipo: " + this.getTypeString() + "\nEl día es: " + this.getDay() + "\nLa hora de comienzo es: " + this.getStartHour() + "\nEl costo es: " + this.getCost() + "\nLa capacidad es " + this.getMaxCap() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        boolean ret = false;
        Activity activityParm = (Activity) o;
        if (this.getDay() == activityParm.getDay()) {
            if (this.getStartHour() == activityParm.getStartHour() && this.getTypeString().equals(activityParm.getTypeString())) {
                ret = true;
            }
        }
        return ret;
    }

    @Override
    public int compareTo(Object o) {
        int ret = this.getDay() - ((Activity) o).getDay();
        if (ret == 0) {
            ret = this.getTypeNumeric() - ((Activity) o).getTypeNumeric();
        }
        return ret;
    }
}

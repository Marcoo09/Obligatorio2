package obligatorio2;

/**
 * @author Marco Fiorito and Felipe Najson
 */
import java.util.*;

public class Prueba {

    public static void main(String[] args) {
        //Variables of Members
        Member member1;
        Member member2;
        //Variable of Activity
        Activity activity1;
        //Variables of inscription
        Inscription inscription1 = null;
        Inscription inscription2 = null;
        //Variables of menu
        int entry;
        boolean executeProgram = true;
        int memberToUpdate;
        int counter;
        boolean inscriptionsMaded = false;
        //Intialize Members
        member1 = defineMember();
        member2 = defineMember();
        // Intitialize Activity
        activity1 = defineActivity();

        //Begin the menu execution
        while (executeProgram) {
            System.out.println("\n<--------INGRESE UNA DE LAS SIGUIENTES OPCIONES DEL MENÚ(NÚMERO)-------->\n"
                    + "\n 1 - Actualizar de datos de socio "
                    + "\n 2 - Inscribirse a actividades "
                    + "\n 3 - Listar inscripciones "
                    + "\n 4 - Actualizar hora de actividad "
                    + "\n 5 - Mostrar datos guardados "
                    + "\n 6 - Salir\n");
            entry = Prueba.askForNumeric("opción");
            //Depending on entry variable, we execute different options
            switch (entry) {
                case 1:
                    counter = 1;
                    System.out.println("\n<--------Socio[1]-------->");
                    System.out.println(member1);
                    System.out.println("\n<--------Socio[2]-------->");
                    System.out.println(member2 + "\n");
                    do {
                        //Validate if the typed number is a correct member
                        if (counter > 1) {
                            System.out.println("INGRESE UN SOCIO UN VÁLIDO");
                        }
                        counter += 1;
                        memberToUpdate = Prueba.askForNumeric("número socio a modificar");
                        if (memberToUpdate == 1) {
                            Prueba.updateMember(member1);
                        } else if (memberToUpdate == 2) {
                            Prueba.updateMember(member2);
                        }
                    }while (!Prueba.validateAttribute(memberToUpdate, 1, 2));
                    break;
                case 2:
                    //Inscribe an activity
                    inscriptionsMaded = true;
                    inscription1 = Prueba.defineInscription(member1, activity1);
                    System.out.println("Inscripción 1 realizada correctamente\n");
                    inscription2 = Prueba.defineInscription(member2, activity1);
                    System.out.println("Inscripción 2 realizada correctamente\n");
                    break;
                case 3:
                    //Validate if we have inscriptions registered
                    if (inscriptionsMaded) {
                        //Show the inscription in order of the pickUptime
                        System.out.println(inscription1.showOrderedInscriptions(inscription2));
                    } else {
                        System.out.println("No hay inscripciones registradas\n");
                    }
                    break;
                case 4:
                    //Update Hour of the Activity and if there are inscriptions also update these in proportion.
                    if (inscriptionsMaded) {
                        Prueba.updateHour(activity1, inscription1, inscription2);
                    } else {
                        Prueba.updateHour(activity1);
                    }
                    break;
                case 5:
                    //Show all application data
                    System.out.println("\n<--------Actividad-------->\n" + activity1 + "\n<--------Miembros-------->\n" + member1 + "\n" + member2);
                    //If there are inscriptions also show them
                    if (inscriptionsMaded) {
                        System.out.println("\n<--------Inscripciones-------->\n" + inscription1 + "\n" + inscription2);
                    }
                    break;
                case 6:
                    //Finalize the program
                    executeProgram = false;
                    System.out.println("Ha finalizado la ejecución del programa");
                    break;
                default:
                    System.out.println("OPCIÓN NO VÁLIDA");
                    break;
            }
        }
    }

    //This method ask for a String and return the value
    public static String askForString(String whatToAsk) {
        Scanner inputString = new Scanner(System.in);
        System.out.print("Ingrese " + whatToAsk + ": ");
        return inputString.nextLine();
    }

    //This method ask for a Number and return the value
    public static int askForNumeric(String whatToAsk) {
        Scanner inputNumeric = new Scanner(System.in);
        System.out.print("Ingrese " + whatToAsk + ": ");
        return inputNumeric.nextInt();
    }

    //Update Activty hour and Inscriptions pick up time (maintaining the proportion)
    public static void updateHour(Activity activity, Inscription inscription1, Inscription inscription2) {
        //New values of the variables
        int newActivityHour = 0;
        int newPickUpTime = 0;

        //Variables of Validators
        boolean hourValidator = false;

        //Validation of the hour
        while (!hourValidator) {
            newActivityHour = Prueba.askForNumeric("nueva hora de actividad");

            hourValidator = Prueba.validateAttribute(newActivityHour, 7, 21);
            if (!hourValidator) {
                System.out.println("INGRESE UNA HORA VÁLIDA");
            }
        }
        //Calculate the new pickUpTime
        newPickUpTime = inscription1.getPickUpTime() + (newActivityHour - activity.getStartHour());
        newPickUpTime = Prueba.validatePickUpTime(newPickUpTime);
        //Set the new pickUpTime for the inscription1
        inscription1.setPickUpTime(newPickUpTime);

        newPickUpTime = inscription2.getPickUpTime() + (newActivityHour - activity.getStartHour());
        newPickUpTime = Prueba.validatePickUpTime(newPickUpTime);
        //Set the new pickUpTime for the inscription2
        inscription2.setPickUpTime(newPickUpTime);
        //Set the new startHour
        activity.setStartHour(newActivityHour);
        System.out.println("Horas actualizadas correctamente \n");
    }

    //Update only activity hour
    public static void updateHour(Activity activity) {
        //New variables of values
        int newActivityHour = 0;
        //Variables of validators
        boolean hourValidator = false;

        //Validation of hour
        while (!hourValidator) {
            newActivityHour = Prueba.askForNumeric("nueva hora de actividad.");

            hourValidator = Prueba.validateAttribute(newActivityHour, 7, 21);
            if (!hourValidator) {
                System.out.println("INGRESE UNA HORA VÁLIDA");
            }
        }
        //Set the new StartHour
        activity.setStartHour(newActivityHour);
        System.out.println("Hora actualizada correctamente \n");

    }

    //Define Inscription
    public static Inscription defineInscription(Member aMember, Activity activity) {
        //Variable of the inscription
        int pickUpTime = 0;
        //Variable used in the validator
        boolean hourValidator = false;
        boolean pickUpValidator = false;

        System.out.print("\nEl socio que estás modificando es: " + aMember.getName());
        System.out.println(activity);

        //Validator of pickup range hour
        while (!hourValidator || !pickUpValidator) {
            pickUpTime = Prueba.askForNumeric("hora a la que quieres te pasen a buscar");
            //Check if the pick up time is in the correct range
            hourValidator = Prueba.validateAttribute(pickUpTime, 6, 20);

            if (hourValidator) {
                //Check if the pick up time is at least one hour before the activity
                pickUpValidator = (Math.abs(activity.getStartHour() - pickUpTime) >= 1);
                if (!pickUpValidator) {
                    System.out.println("INGRESE UNA HORA QUE SEA POR LO MENOS UNA HORA ANTES DE: " + activity.getStartHour());
                }
            } else {
                System.out.println("INGRESE UNA HORA ENTRE 6 Y 20");
            }
        }
        //return the new object Inscription
        return new Inscription(aMember, activity, pickUpTime);
    }

    //Define Activity
    public static Activity defineActivity() {

        //Variables of the activity
        int day = 0;
        int startHour = 0;
        int maxCap;
        float cost;
        int choosedType = 0;

        //Variables used in the validators
        boolean typeValidator = false;
        boolean dayValidator = false;
        boolean hourValidator = false;

        System.out.println("\n<--------Ingrese los datos de una actividad-------->\n ");

        System.out.println("Elige uno de los siguientes tipos (número):\n "
                + "\n1-Teatro "
                + "\n2-Cine"
                + "\n3-Viaje"
                + "\n4-Paseo local"
                + "\n5-Paseo interdepartamental"
                + "\n6-Caminata"
                + "\n7- Otro\n");

        //Validation of the type
        while (!typeValidator) {
            choosedType = Prueba.askForNumeric("opción");
            typeValidator = Prueba.validateAttribute(choosedType, 1, 7);
            if (!typeValidator) {
                System.out.println("INGRESE UN TIPO VÁLIDO");
            }
        }
        //Validation of the day
        while (!dayValidator) {
            day = Prueba.askForNumeric("día");
            dayValidator = Prueba.validateAttribute(day, 1, 31);
            if (!dayValidator) {
                System.out.println("INGRESE UN DÍA VÁLIDO");
            }
        }
        //Validation of the hour
        while (!hourValidator) {
            startHour = Prueba.askForNumeric("hora de comienzo");
            hourValidator = Prueba.validateAttribute(startHour, 7, 21);
            if (!hourValidator) {
                System.out.println("INGRESE UNA HORA VÁLIDA");
            }
        }
        cost = Prueba.askForNumeric("el costo");
        maxCap = Prueba.askForNumeric("capacidad máxima");
        //return the new object Activity
        return new Activity(day, choosedType, startHour, maxCap, cost);
    }

    //Define Member
    public static Member defineMember() {

        //Variables of Member
        String name;
        int age = 0;
        int dni;
        String address;
        long contactNumber;

        //Variables used in validators
        boolean ageValidator = false;

        System.out.println("\n<--------Ingrese los datos del miembro-------->\n ");

        name = Prueba.askForString("nombre");

        //Validation of age
        while (!ageValidator) {
            age = Prueba.askForNumeric("edad");
            ageValidator = Prueba.validateAttribute(age, 0, 120);
            if (!ageValidator) {
                System.out.println("INGRESE UNA EDAD VÁLIDA");
            }
        }
        dni = Prueba.askForNumeric("dni");
        address = Prueba.askForString("dirección");
        contactNumber = Prueba.askForNumeric("número de contacto");

        //return the new object Member
        return new Member(name, age, dni, address, contactNumber);
    }

    //Update member age and member address
    public static void updateMember(Member aMember) {
        //New values of the attributes
        int newAge = 0;
        String newAddress;
        //Variables used in validators
        boolean ageValidator = false;

        //Validation of the age
        while (!ageValidator) {
            newAge = Prueba.askForNumeric("nueva edad");
            ageValidator = Prueba.validateAttribute(newAge, 0, 120);
            if (!ageValidator) {
                System.out.println("INGRESE UNA EDAD VÁLIDA");
            }
        }
        newAddress = Prueba.askForString("nueva dirección");
        //Sett the new values
        aMember.setAge(newAge);
        aMember.setAdress(newAddress);
    }

    //Range Validator
    public static boolean validateAttribute(int numberToValidate, int intialRange, int finalRange) {
        //Check if the first parameter is between the range
        return (numberToValidate >= intialRange && numberToValidate <= finalRange);
    }

    //Modify the pickuptime if this is out of range
    public static int validatePickUpTime(int newPickUpTime) {
        //When the newPickUpTime is out of range we evaluate which case is and approximate this to 6 or 20
        //First Case
        if (newPickUpTime > 20) {
            newPickUpTime -= 24;
            if (newPickUpTime <= 0) {
                newPickUpTime = 20;
                System.out.println("Se ha aproximado la hora de recogida a la hora más cercana.");
            } else if (newPickUpTime > 0 && newPickUpTime < 6) {
                newPickUpTime = 6;
                System.out.println("Se ha aproximado la hora de recogida a la hora más cercana.");
            }
        } //Second case
        else if (newPickUpTime < 6) {
            newPickUpTime += 24;
            if (newPickUpTime <= 24) {
                newPickUpTime = 20;
                System.out.println("Se ha aproximado la hora de recogida a la hora más cercana.");
            } else if (newPickUpTime > 24) {
                newPickUpTime = 6;
                System.out.println("Se ha aproximado la hora de recogida a la hora más cercana.");
            }
        }
        return newPickUpTime;
    }
}

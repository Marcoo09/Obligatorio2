package obligatorio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Marco Fiorito and Felipe Najson
 */
public class Prueba {

    public static void main(String[] args) {
        //Variables of Members
        Member member = null;
        //Variable of Activity
        Activity activity = null;
        //Variables of inscription
        Inscription inscription = null;
        //Variables of Animator
        Animator animator = null;
        //Variable of arraylists domain class
        DataBase db = new DataBase();
        //Variables of menu
        int entry = 0;
        String[] menuOptions = {"Registrar socio", "Registrar animador", "Registrar actividad", "Registrar inscripción a actividad", "Consulta por actividad", "Listado ordenado de actividades", "Baja de actividad", "Mostrar cuales son los tipos de actividades preferidas", "Consulta de animadores sin actividades asignadas", "Salir"};
        boolean executeProgram = true;
        boolean animatorCreated = false;
        boolean memberCreated = false;
        boolean activityCreated = false;
        boolean inscriptionCreated = false;
        //Scanner used in the error handling.
        Scanner input = new Scanner(System.in);
        //Begin the menu execution
        System.out.println("..:: Bienvenido al sistema de gestión de Entretiene 3Edad SA ::..");
        while (executeProgram) {

            System.out.println("\n<--------INGRESE UNA DE LAS SIGUIENTES OPCIONES DEL MENÚ(NÚMERO)-------->\n");
            for (int i = 0; i < menuOptions.length; i++) {
                System.out.println((i + 1) + " - " + menuOptions[i]);
            }
            System.out.print("\n");
            entry = Prueba.askForNumeric("opción");
            //Verify if the differents arraylist are empty or not to after validate in the menu options 
            activityCreated = db.getListOfActivities().isEmpty();
            inscriptionCreated = db.getListOfInscriptions().isEmpty();
            memberCreated = db.getListOfMembers().isEmpty();
            animatorCreated = db.getListOfAnimators().isEmpty();
            //Depending on entry variable, we execute different options
            switch (entry) {
                case 1:
                    member = Prueba.defineMember();
                    db.addMember(member);
                    System.out.println("El socio se ha registrado correctamente.");
                    break;
                case 2:
                    animator = Prueba.defineAnimator();
                    db.addAnimator(animator);
                    System.out.println("El animador se ha registrado correctamente,");
                    break;
                case 3:
                    activity = Prueba.defineActivity(db, !animatorCreated);
                    db.addActivity(activity);
                    System.out.println("La actividad se ha registrado correctamente.");
                    break;
                case 4:
                    if (!memberCreated && !activityCreated) {
                        inscription = Prueba.defineInscription(db);
                        db.getListOfInscriptions().add(inscription);
                        System.out.println("La inscripción se ha ingresado correctamente.");
                    } else {
                        System.out.println("Debes registrar previamente al menos una actividad y un socio.");
                    }
                    break;
                case 5:
                    if (!activityCreated) {
                        Prueba.showQtyOfActivitiesOfADay(db);
                    } else {
                        System.out.println("Debes registrar previamente al menos una actividad.");
                    }
                    break;
                case 6:
                    if (!activityCreated) {
                        db.sortActivities();
                        Prueba.showActivityList(db);
                    } else {
                        System.out.println("Debes registrar previamente al menos una actividad.");
                    }
                    break;
                case 7:
                    if (!activityCreated) {
                        Prueba.removeActivity(db);
                        System.out.println("La actividad se ha removido correctamente.");
                    } else {
                        System.out.println("No hay actividades ingresadas");
                    }
                    break;
                case 8:
                    if (!activityCreated && !inscriptionCreated) {
                        Prueba.showFavActivities(db);
                    } else {
                        System.out.println("Debes registrar previamente al menos una actividad y una inscripción.");
                    }
                    break;
                case 9:
                    if (!animatorCreated) {
                        Prueba.showAnimatorsWithoutActivities(db);
                    } else {
                        System.out.println("Debes registrar previamente al menos un animador.");
                    }
                    break;
                case 10:
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
        boolean repeat = true;
        Scanner inputNumeric = new Scanner(System.in);
        int ret = 0;

        while (repeat) {
            try {
                System.out.print("Ingrese " + whatToAsk + ": ");
                ret = inputNumeric.nextInt();
                repeat = false;
            } catch (InputMismatchException e) {
                if (e.toString().equals("java.util.InputMismatchException")) {
                    System.out.println("Debes ingresar un número");
                } else {
                    System.out.println("Debes ingresar un número más corto");
                }
                repeat = true;
                inputNumeric.next();
            }
        }
        return ret;
    }

    //Range Validator
    public static boolean validateAttribute(int numberToValidate, int intialRange, int finalRange) {
        //Check if the first parameter is between the range
        return (numberToValidate >= intialRange && numberToValidate <= finalRange);
    }

    //Define Member
    public static Member defineMember() {
        //Variables of Member
        String name;
        int age = 0;
        int dni = 0;
        String address;
        //Variables used in validators
        boolean ageValidator = false;
        boolean dniValidator = false;
        boolean existMember = false;

        System.out.println("\n<--------Ingrese los datos del socio-------->\n ");

        name = Prueba.askForString("nombre");
        //Validation of age
        while (!ageValidator) {
            age = Prueba.askForNumeric("edad");
            ageValidator = Prueba.validateAttribute(age, 0, 120);
            if (!ageValidator) {
                System.out.println("INGRESE UNA EDAD VÁLIDA");
            }
        }
        //Validation of dni
        while (!dniValidator) {
            dni = Prueba.askForNumeric("dni");
            dniValidator = Prueba.validateAttribute(dni, 0, Integer.MAX_VALUE);
            if (!dniValidator) {
                System.out.println("INGRESE UN DNI VÁLIDO");
            } else {
                //Validate if the member dni is already entered
                existMember = Person.PersonContact.containsKey(dni);
                if (existMember) {
                    System.out.println("EL DNI INGRESADO YA EXISTE");
                    dniValidator = false;
                }
            }
        }

        address = Prueba.askForString("dirección");

        //return the new object Member
        return new Member(name, age, dni, address);
    }

    //Define Animator
    public static Animator defineAnimator() {
        //Variables of Member
        String name;
        int age = 0;
        int dni = 0;
        int yearsOfExperience = 0;
        //Variables used in validators
        boolean ageValidator = false;
        boolean yearsValidator = false;
        boolean dniValidator = false;
        System.out.println("\n<--------Ingrese los datos del Animador-------->\n ");

        name = Prueba.askForString("nombre");

        //Validation of age
        while (!ageValidator) {
            age = Prueba.askForNumeric("edad");
            ageValidator = Prueba.validateAttribute(age, 15, 120);
            if (!ageValidator) {
                System.out.println("INGRESE UNA EDAD VÁLIDA");
            }
        }
        //Validation of dni
        while (!dniValidator) {
            dni = Prueba.askForNumeric("dni");
            dniValidator = Prueba.validateAttribute(dni, 0, Integer.MAX_VALUE);
            if (!dniValidator) {
                System.out.println("INGRESE UN DNI VÁLIDO");
            }
        }

        //Validation of years of experiencie
        while (!yearsValidator) {
            yearsOfExperience = Prueba.askForNumeric("años de experiencia");
            yearsValidator = Prueba.validateAttribute(yearsOfExperience, 0, age - 14);
            if (!yearsValidator) {
                System.out.println("INGRESE UNA CANTIDAD VÁLIDA");
            }
        }
        //return the new object Member
        return new Animator(name, age, dni, yearsOfExperience);
    }

    //Define Activity
    public static Activity defineActivity(DataBase aDb, boolean animatorCreated) {
        //Variables of the activity
        int day = 0;
        int startHour = 0;
        int maxCap = 0;
        int cost = 0;
        int choosedType = 0;
        Animator animator = null;
        int numAnimator = 0;
        //Scanner used only with cost
        Scanner input = new Scanner(System.in);
        //Variables used in the validators
        boolean typeValidator = false;
        boolean dayValidator = false;
        boolean hourValidator = false;
        boolean maxCapValidator = false;
        boolean costValidator = false;
        boolean animatorValidator = false;

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
        //Validation of the cost
        while (!costValidator) {
            cost = Prueba.askForNumeric("el costo");
            costValidator = Prueba.validateAttribute((int) cost, 0, Integer.MAX_VALUE);
            if (!costValidator) {
                System.out.println("INGRESE UN COSTO VÁLIDO");
            }
        }
        //Validation of the max cap
        while (!maxCapValidator) {
            maxCap = Prueba.askForNumeric("capacidad máxima");
            maxCapValidator = Prueba.validateAttribute(maxCap, 1, Integer.MAX_VALUE);
            if (!maxCapValidator) {
                System.out.println("INGRESE UNA CAPACIDAD VÁLIDA");
            }
        }

        //Validation of Animator
        if (animatorCreated) {
            while (!animatorValidator) {
                Prueba.showAnimatorList(aDb);
                numAnimator = Prueba.askForNumeric("numero del animador que deseas seleccionar");
                animatorValidator = Prueba.validateAttribute(numAnimator, 1, aDb.getListOfAnimators().size());
                if (!animatorValidator) {
                    System.out.println("INGRESE UN ANIMADOR EXISTENTE");
                } else {
                    animator = aDb.getListOfAnimators().get(numAnimator - 1);
                }
            }
        } else {
            System.out.println("No hay animadores disponibles para elegir");
        }

        //return the new object Activity
        return new Activity(day, choosedType, startHour, maxCap, cost, animator);
    }

    //Define Inscription
    public static Inscription defineInscription(DataBase aDb) {
        //Variable of the inscription
        int pickUpTime = 0;
        long contactNumber = 0;
        int numMember = 0;
        int numActivity = 0;
        int maxCap = 0;
        //Variable used in the validator
        boolean hourValidator = false;
        boolean pickUpValidator = false;
        boolean memberValidator = false;
        boolean activityValidator = false;
        //Objects
        Member member = null;
        Activity activity = null;
        //Lists
        ArrayList<Inscription> listOfInscriptions = aDb.getListOfInscriptions();

        //Select and validate Member 
        while (!memberValidator) {
            Prueba.showMemberList(aDb.getListOfMembers(), false);
            System.out.print("\n");
            numMember = Prueba.askForNumeric("numero del socio que deseas seleccionar");
            memberValidator = Prueba.validateAttribute(numMember, 1, aDb.getListOfMembers().size());
            if (!memberValidator) {
                System.out.println("INGRESE UN SOCIO EXISTENTE");
            } else {
                member = aDb.getListOfMembers().get(numMember - 1);
            }
        }

        //Select and Validate Activity
        while (!activityValidator) {
            boolean memberInscripted = false;
            Prueba.showActivityList(aDb);
            System.out.print("\n");
            numActivity = Prueba.askForNumeric("numero de la actividad que deseas seleccionar");
            activityValidator = Prueba.validateAttribute(numActivity, 1, aDb.getListOfActivities().size());
            if (!activityValidator) {
                System.out.println("INGRESE UNA ACTIVIDAD EXISTENTE");
            } else {
                //Validate if the activity has capacity
                if (aDb.getListOfActivities().get(numActivity - 1).getMaxCap() > 0) {
                    //Activity choosed
                    activity = aDb.getListOfActivities().get(numActivity - 1);
                    //Verify if the member is inscripted in this activity
                    for (int i = 0; i < listOfInscriptions.size(); i++) {
                        if (listOfInscriptions.get(i).getActivity().equals(activity)) {
                            if (listOfInscriptions.get(i).getMember().equals(member)) {
                                System.out.println("Este socio ya está inscripto a la actividad.");
                                memberInscripted = true;
                                activityValidator = false;
                            }
                        }
                    }
                    //If the member is not inscripted in the activity, we set the new maxcap to the activity
                    if (!memberInscripted) {
                        maxCap = aDb.getListOfActivities().get(numActivity - 1).getMaxCap();
                        aDb.getListOfActivities().get(numActivity - 1).setMaxCap(maxCap - 1);
                    }
                    //Set new max cap (decrement one place)
                } else {
                    activityValidator = false;
                    System.out.println("INGRESE UNA ACTIVIDAD CON CUPOS");
                }
            }
        }

        //Validator of pickup range hour
        while (!hourValidator || !pickUpValidator) {
            pickUpTime = Prueba.askForNumeric("hora a la que quieres te pasen a buscar");
            //Check if the pick up time is in the correct range
            hourValidator = Prueba.validateAttribute(pickUpTime, 6, 20);

            if (hourValidator) {
                //Check if the pick up time is at least one hour before the activity
                pickUpValidator = ((activity.getStartHour() - pickUpTime) >= 1);
                if (!pickUpValidator) {
                    System.out.println("INGRESE UNA HORA QUE SEA POR LO MENOS UNA HORA ANTES DE: " + activity.getStartHour());
                }
            } else {
                System.out.println("INGRESE UNA HORA ENTRE 6 Y 20");
            }
        }
        contactNumber = Prueba.askForNumeric("el número de contacto");
        //return the new object Inscription
        return new Inscription(member, activity, pickUpTime, contactNumber);
    }

    //Show qty of activities per type in a day
    public static void showQtyOfActivitiesOfADay(DataBase aDb) {
        //Variables of the DataBase
        ArrayList<Activity> listOfActivities = aDb.getListOfActivities();
        Activity auxActivity = new Activity();
        //Entry variables
        int day = 0;
        //Variables used in for
        int[] qtyOfTypes = new int[8];
        //Variables of validation
        boolean dayValidator = false;
        boolean activityValidator = false;

        while (!dayValidator) {
            day = Prueba.askForNumeric("el día");
            dayValidator = Prueba.validateAttribute(day, 1, 31);
            if (!dayValidator) {
                System.out.println("INGRESE UN DÍA VÁLIDO");
            }
        }
        auxActivity.setDay(day);
        //Verify if exist some activity on this day
        for (int j = 0; j < listOfActivities.size(); j++) {
            if (listOfActivities.get(j).getDay() == auxActivity.getDay()) {
                activityValidator = true;
            }
        }

        if (activityValidator) {
            //Load the array with qty of activities per Type
            for (int i = 0; i < listOfActivities.size(); i++) {
                auxActivity = listOfActivities.get(i);
                if (day == auxActivity.getDay()) {
                    qtyOfTypes[auxActivity.getTypeNumeric()] += 1;
                }
            }
            System.out.println("• Día: " + day);
            for (int j = 1; j < qtyOfTypes.length; j++) {
                if (qtyOfTypes[j] != 0) {
                    System.out.print("\n" + j + " ");
                    for (int i = 1; i <= qtyOfTypes[j]; i++) {
                        System.out.print("*");
                    }
                }
            }
        } else {
            System.out.println("No hay actividades este día");
        }
    }
    
    //Show animator list of the system
    public static void showAnimatorList(DataBase aDb) {
        int i = 1;
        Iterator<Animator> it = aDb.getListOfAnimators().iterator();
        while (it.hasNext()) {
            Animator animator = it.next();
            System.out.print("\n• Animador[" + (i) + "]\n" + "" + (animator) + "\n");
            i++;
        }
    }
    //Show member list with all information of member of only name and number
    public static void showMemberList(ArrayList<Member> listOfMember, boolean onlyName) {
        int i = 1;
        //Variables to the member
        int memberDni = 0;
        long memberNumber = 0;

        Iterator<Member> it = listOfMember.iterator();

        while (it.hasNext()) {
            Member member = it.next();
            if (onlyName) {
                memberDni = member.getDni();
                memberNumber = Person.PersonContact.get(memberDni);
                System.out.print("\n• Socio[" + (i) + "]\n" + "" + (member.getName()) + "\n" + "Y el número de contacto es: " + memberNumber + "\n");
            } else {
                System.out.print("\n• Socio[" + (i) + "]\n" + "" + (member) + "\n");
            }
            i++;
        }
    }
    
    //Show Activity List
    public static void showActivityList(DataBase aDb) {
        int i = 1;
        Iterator<Activity> it = aDb.getListOfActivities().iterator();
        while (it.hasNext()) {
            Activity activity = it.next();
            System.out.print("\n• Actividad[" + (i) + "]\n" + "" + (activity) + "\n");
            i++;
        }
    }
    //Shows a list depending on the arraylist parameter. It used for example in the option 9 of the menu
    public static void showAnimatorList(ArrayList<Animator> listOfAnimator) {
        int i = 1;
        Iterator<Animator> it = listOfAnimator.iterator();
        while (it.hasNext()) {
            Animator animator = it.next();
            System.out.print("\n• Animador[" + (i) + "]\n" + "" + (animator.getName()) + "\n");
            i++;
        }
    }
    
    //Remove a activity and give a list of Members inscripted to call
    public static void removeActivity(DataBase aDb) {
        //Returned arraylist
        ArrayList<Member> membersToCall = new ArrayList<>();
        ArrayList<Inscription> listOfInscriptions = aDb.getListOfInscriptions();
        Activity activityToRemove = null;
        Iterator<Inscription> it = listOfInscriptions.iterator();
        Inscription auxInscription = null;

        ArrayList<Inscription> newInscriptionList = new ArrayList<>();

        //Variables of valdiation
        boolean activityValidator = false;
        int numActivity = 0;

        //Select and Validate Activity
        while (!activityValidator) {
            Prueba.showActivityList(aDb);
            System.out.print("\n");
            numActivity = Prueba.askForNumeric("numero de la actividad que deseas borrar");
            activityValidator = Prueba.validateAttribute(numActivity, 1, aDb.getListOfActivities().size());
            if (!activityValidator) {
                System.out.println("INGRESE UNA ACTIVIDAD EXISTENTE");
            }
        }
        activityToRemove = aDb.getListOfActivities().get(numActivity - 1);

        while (it.hasNext()) {
            auxInscription = it.next();
            if (auxInscription.getActivity().equals(activityToRemove)) {
                membersToCall.add(auxInscription.getMember());
            } else {
                newInscriptionList.add(auxInscription);
            }
        }
        aDb.setListOfInscriptions(newInscriptionList);
        aDb.getListOfActivities().remove(activityToRemove);
        if (!membersToCall.isEmpty()) {
            System.out.println("Los socios a llamar por la baja de actividad son:");
            Collections.sort(membersToCall);
            Prueba.showMemberList(membersToCall, true);
        } else {
            System.out.println("No hay socios a llamar por esta actividad");
        }

    }
    
    public static void showFavActivities(DataBase aDb) {
        int max = -1;
        int[] activities = new int[8];
        boolean[] favActivities = new boolean[8];
        Iterator<Inscription> it = aDb.getListOfInscriptions().iterator();
        Inscription auxInscription;

        //Charge the array activities with the qty of inscriptions per activity
        while (it.hasNext()) {
            auxInscription = it.next();
            int num = auxInscription.getActivity().getTypeNumeric();
            activities[num] += 1;
        }
        //Search the activity with the max number of inscriptions
        for (int i = 1; i < activities.length; i++) {
            if (activities[i] > max) {
                max = activities[i];
            }
        }
        System.out.println("Los tipos de actividad favoritas son:");
        //Search the activities who have the max number of inscriptions
        for (int i = 1; i < activities.length; i++) {
            if (activities[i] == max) {
                System.out.println("\n" + Activity.OptionsTypes[i]);
            }
        }

    }

    public static void showAnimatorsWithoutActivities(DataBase aDb) {
        //List of animators and activities
        ArrayList<Activity> listOfActivities = aDb.getListOfActivities();
        ArrayList<Animator> listOfAnimators = aDb.getListOfAnimators();
        //Variables used to find the animators without activity
        ArrayList<Animator> animatorsWithActivity = new ArrayList<>();
        ArrayList<Animator> animatorsWithoutActivity = new ArrayList<>();
        Animator actualAnimator = null;
        //Iterators
        Iterator<Activity> itActivity = listOfActivities.iterator();
        //Add in a list the animators with activity
        while (itActivity.hasNext()) {
            animatorsWithActivity.add(itActivity.next().getAnimator());
        }
        //Find the animators without activity asigned
        for (int i = 0; i < listOfAnimators.size(); i++) {
            actualAnimator = listOfAnimators.get(i);
            if (animatorsWithActivity.indexOf(actualAnimator) == -1) {
                animatorsWithoutActivity.add(actualAnimator);
            }
        }
        //Verify if the list is empty to show this or a message
        if (!animatorsWithoutActivity.isEmpty()) {
            System.out.println("Los animadores sin actividades asignadas son: ");
            Prueba.showAnimatorList(animatorsWithoutActivity);
        } else {
            System.out.println("No hay animadores sin actividades asignadas");
        }

    }
}

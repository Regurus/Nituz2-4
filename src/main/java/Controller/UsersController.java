package Controller;



import Model.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UsersController{
    private ArrayList<Dispatcher> dispatchers;
    private ArrayList<EmergencyMedicalTechnician> emergencyMedicalTechnicians;
    private ArrayList<Policeman> policemen;
    private ArrayList<Fireman> firemen;
    private ComplaintDatabase complaintDB;
    private UsersDatabase usersDB;
    private static UsersController usersController = null;

    private UsersController(UsersDatabase usersDB ,ComplaintDatabase complaintDB)
    {
        if(usersController == null){
            this.dispatchers = usersDB.getAllDispatchers("Dispatcher");
            this.emergencyMedicalTechnicians = usersDB.getAllEmergencyMedicalTechnicians("EmergencyMedicalTechnician");
            this.policemen = usersDB.getAllPolicemen("Policeman");
            this.firemen = usersDB.getAllFiremen("Fireman");
            this.usersDB = usersDB;
            this.complaintDB = complaintDB;
        }
    }

    public static UsersController UsersControllerInstance(UsersDatabase usersDB ,ComplaintDatabase complaintDB) {
        if (usersController == null)
            usersController = new UsersController(usersDB ,complaintDB);
        return usersController;
    }

    private static String currentLogin;//should be updated to null on exit
    static UsersDatabase activeConnection;
    public UsersController(){
        if(activeConnection==null)
        {
            activeConnection = new UsersDatabase();
            dispatchers = new ArrayList<Dispatcher>();
            emergencyMedicalTechnicians = new ArrayList<EmergencyMedicalTechnician>();
            policemen = new ArrayList<Policeman>();
            firemen = new ArrayList<Fireman>();
        }
    }

    public static void addComplaint(Complaint complaint) {
       // ComplaintDatabase.createComplaint(complaint);
        //write the complaint addition to logger
    }


    public boolean combinationApprove(String login,String password){
        User account = activeConnection.getByUsername(login);
        if(account==null)
            return false;
        return account.getPassword().equals(password);
    }
    public boolean passwordApprove(String password){
        //approves only passwords that include at least 1 number, 1 capital letter and one regular letter.
        return Pattern.matches("[0-9]", password) && Pattern.matches("[a-z]", password) && Pattern.matches("[A-Z]", password);
    }
    private static void setCurrentUser(String user){
        currentLogin=user;
    }
    public static void nullifyCurrentUser(){
        currentLogin=null;
    }
    public static String getCurrentUser(){
        return currentLogin;
    }
    public static void endSession(){
        activeConnection.closeConnection();
    }
}

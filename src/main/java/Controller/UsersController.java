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
    private static int complaintID = 1;
    private static String currentLogin;//should be updated to null on exit TODO

    private UsersController(UsersDatabase usersDB ,ComplaintDatabase complaintDB)
    {
        if(usersController == null){
            this.dispatchers = usersDB.getAllDispatchers();
            this.emergencyMedicalTechnicians = usersDB.getAllEmergencyMedicalTechnicians();
            this.policemen = usersDB.getAllPolicemen();
            this.firemen = usersDB.getAllFiremen();
            this.usersDB = usersDB;
            this.complaintDB = complaintDB;

        }
    }

    public static UsersController UsersControllerInstance(UsersDatabase usersDB ,ComplaintDatabase complaintDB) {
        if (usersController == null)
            usersController = new UsersController(usersDB ,complaintDB);
        return usersController;
    }

    public int getAvailableComplaintID(){
        return complaintID;
    }

    public void incrementComplaintID(){
        complaintID++;
    }

    public static void addComplaint(Complaint complaint) {
       // ComplaintDatabase.createComplaint(complaint);

        //write the complaint addition to logger

    }



    public boolean approveLogIn(String login,String password){
        User account = usersDB.getByUsername(login);
        if(account==null){
            return false;
        }
        return account.getPassword().equals(password);
    }//TODO check admin!

    public User getUserByUsername(String username){
        return null;
    }//TODO

    public boolean createNewComplaint(){
        return true;
    }//TODO

    public void removeComplaint(int coimplaintId){

    }//TODO

    public void createNewWarning(String usernameDest, int complaintId){

    }//TODO

    public ArrayList<Complaint> getAllComplaints(String division){return null;}//TODO

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
}

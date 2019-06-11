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
    private AdminDatabase adminUserDB;
    private static UsersController usersController = null;
    private static int complaintID = 1;
    private User loginUser;
    private String username;//should be updated to null on exit TODO
    private EASystem system;



    private UsersController(UsersDatabase usersDB ,ComplaintDatabase complaintDB, AdminDatabase adminUserDB)
    {
        if(usersController == null){
            this.dispatchers = usersDB.getAllDispatchers();
            this.emergencyMedicalTechnicians = usersDB.getAllEmergencyMedicalTechnicians();
            this.policemen = usersDB.getAllPolicemen();
            this.firemen = usersDB.getAllFiremen();
            this.usersDB = usersDB;
            this.complaintDB = complaintDB;
            this.adminUserDB = adminUserDB;
            this.system = EASystem.eaSystemInstance();
        }
    }

    public static UsersController UsersControllerInstance() {
        if (usersController == null)
            usersController = new UsersController(new UsersDatabase() ,new ComplaintDatabase(), new AdminDatabase());
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
            AdminUser admin = adminUserDB.getByUsernameAndPassword(login, password);
            if(admin == null)
                return false;
            system.setAdmin(admin);
            return true;
        }
        if(!account.getPassword().equals(password))
            return false;
        username = account.getUserName();
        this.loginUser = account;
        return true;
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

    public String getCurrentUsername(){
        return username;
    }
}

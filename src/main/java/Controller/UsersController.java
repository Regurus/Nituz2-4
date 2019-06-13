package Controller;



import Model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

public class UsersController implements Observer {
    private ArrayList<Dispatcher> dispatchers;
    private ArrayList<EmergencyMedicalTechnician> emergencyMedicalTechnicians;
    private ArrayList<Policeman> policemen;
    private ArrayList<Fireman> firemen;
    private static ComplaintDatabase complaintDB;
    private UsersDatabase usersDB;
    private AdminDatabase adminUserDB;
    private WarningDatabase warningDB;
    private static UsersController usersController = null;
    private static int complaintID ;
    private User loginUser;
    private ActionLogger actionLogger;



    private UsersController(UsersDatabase usersDB ,ComplaintDatabase complaintDB, AdminDatabase adminUserDB, WarningDatabase warningDB)
    {
        if(usersController == null){
            this.usersDB = usersDB;
            this.complaintDB = complaintDB;
            this.adminUserDB = adminUserDB;
            this.warningDB = warningDB;
            this.dispatchers = usersDB.getAllDispatchers();
            this.emergencyMedicalTechnicians = usersDB.getAllEmergencyMedicalTechnicians();
            this.policemen = usersDB.getAllPolicemen();
            this.firemen = usersDB.getAllFiremen();
            complaintID= complaintDB.getLastIndex()+1;
            this.actionLogger = ActionLogger.actionLoggerInstance();
        }
    }

    public static UsersController UsersControllerInstance() {
        if (usersController == null){
            usersController = new UsersController(new UsersDatabase() ,new ComplaintDatabase(), new AdminDatabase(), new WarningDatabase());
            complaintID = complaintDB.getLastIndex()+1;
        }
        //complaintDB.getLastIndex() returns the last id exist in complaint table
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

    public User getLoginUser() {
        return loginUser;
    }

    public boolean approveLogIn(String login, String password){
        User account = usersDB.getByUsername(login);
        if(account==null){
            AdminUser admin = adminUserDB.getByUsernameAndPassword(login, password);
            if(admin == null)
                return false;
            EASystem.eaSystemInstance().setAdmin(admin);
            return true;
        }
        if(!account.getPassword().equals(password) || account.getStatus().equals("inactive"))
            return false;

        this.loginUser = account;
        loginUser.addObserver(this);
        return true;
    }

    public User getUserByUsername(String username){
        return null;
    }//TODO

    /**
     * first check if source user from same organization as destination user
     * if not- return false, if login user == null returns false
     * if yes - create a complaint and add it to complaint DB
     * @param destination
     * @param description
     * @return
     */
    public boolean createNewComplaint(String destination, String description) {
        if (loginUser != null) {
            User dest = usersDB.getByUsername(destination);
            if(dest!= null){
                if (loginUser.getType().equals(dest.getType()) && dest.getStatus().equals("active")) {
                    loginUser.createComplaint(complaintID, destination, description);
                    complaintID++;
                    return true;
                }
            }
        }
        return false;
    }





    public ArrayList<Complaint> getAllComplaints(String division){
        return complaintDB.getDivisionComplaints(division);
    }



    public boolean passwordApprove(String password){
        //approves only passwords that include at least 1 number, 1 capital letter and one regular letter.
        return Pattern.matches("[0-9]", password) && Pattern.matches("[a-z]", password) && Pattern.matches("[A-Z]", password);
    }



    @Override
    public void update(Observable o, Object arg) {
        complaintDB.createComplaint((Complaint) arg );
        Complaint complaint = (Complaint) arg;
        actionLogger.writeToLog("Complaint created; " + complaint.toString());
    }

    public ArrayList<Complaint> getAllUserWarning(){
        ArrayList<Complaint> complaints = new ArrayList<>();
        if (loginUser!= null){
            ArrayList<Warning> warnings =  warningDB.getAllWarningByDest(loginUser.getUserName());
            for (Warning w:warnings) {
                complaints.add(complaintDB.getByID(w.getComplaintID()));
            }
        }
        return complaints;
    }

}

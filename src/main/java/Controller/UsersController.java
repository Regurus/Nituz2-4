package Controller;



import Model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class UsersController{
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
    private String username;//should be updated to null on exit TODO
    private EASystem system;



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
            this.system = EASystem.eaSystemInstance();
            complaintID= complaintDB.getLastIndex()+1;
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

    /**
     * first check if source user from same organization as destination user
     * if not- return false, if login user == null returns false
     * if yes - create a complaint and add it to complaint DB
     * @param destination
     * @param description
     * @return
     */
    public boolean createNewComplaint(String destination, String description){
        if(loginUser!=null){
            if(usersDB.getByUsername(loginUser.getUserName()).getType().equals(usersDB.getByUsername(destination).getType()))
                return false;
            Complaint complaint = new Complaint(complaintID,loginUser.getUserName(),destination,description,getDateAndTime(),"pending",usersDB.getByUsername(loginUser.getUserName()).getType());
            complaintID++;
            complaintDB.createComplaint(complaint);
            return true;
        }else{
            return false;
        }
    }

    /**
     * if the admin is not approving the complaint we'll call this func
     * change the complaint status to "no"
     * @param complaintId
     */
    public void declineComplaint(int complaintId)
    {
        complaintDB.editConfirmation("no", complaintId);
    }

    public void createNewWarning(String usernameDest, int complaintId){
        if(usernameDest.equals(""))
            return;
        Warning warning = new Warning(complaintId,usernameDest);
        warningDB.createWarning(warning);
        

    }

    public ArrayList<Complaint> getAllComplaints(String division){
        return complaintDB.getDivisionComplaints(division);
    }



    public boolean passwordApprove(String password){
        //approves only passwords that include at least 1 number, 1 capital letter and one regular letter.
        return Pattern.matches("[0-9]", password) && Pattern.matches("[a-z]", password) && Pattern.matches("[A-Z]", password);
    }

    public String getCurrentUsername(){
        return username;
    }

    private String getDateAndTime(){
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        return formatter.format(currentDate);
    }

}

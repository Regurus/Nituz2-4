package Controller;
import Model.*;

public class EASystem {
    private static EASystem eaSystem = null;
    private CategoriesDatabase categoriesDB;
    private ComplaintDatabase complaintDB;
    private AdminUser admin;
    private ActionLogger actionLogger;
    private WarningDatabase warningDB;
    private UsersDatabase usersDB;


    private EASystem(CategoriesDatabase categoriesDB, ComplaintDatabase complaintDB, WarningDatabase warningDB, UsersDatabase usersDB) {
        if(eaSystem == null){
            this.categoriesDB = categoriesDB;
            this.complaintDB = complaintDB;
            this.warningDB = warningDB;
            this.usersDB = usersDB;
            this.actionLogger = ActionLogger.actionLoggerInstance();
        }
    }

    public static EASystem eaSystemInstance() {
        if(eaSystem == null)
        {
            eaSystem = new EASystem(new CategoriesDatabase(), new ComplaintDatabase(), new WarningDatabase(), new UsersDatabase());
        }
        return eaSystem;
    }
    public void reset(){
        eaSystem = null;
        this.admin = null;
    }
    public boolean createNewCategory(String category){
        if (categoriesDB.isExist(category))
            return false;
        categoriesDB.createCategory(category);
        actionLogger.writeToLog("Category created; " + category);
        return true;
    }

    public void setAdmin(AdminUser admin){
        this.admin = admin;
    }

    public AdminUser getAdmin(){
        return this.admin;
    }

    /**
     * if the admin is not approving the complaint we'll call this func
     * change the complaint status to "no"
     * @param complaintId
     */
    public void declineComplaint(int complaintId)
    {
        complaintDB.editConfirmation("no", complaintId);
        Complaint complaint = complaintDB.getByID(complaintId);
        actionLogger.writeToLog("Complaint declined; " + complaint.toString());

    }


    public void createNewWarning(String usernameDest, int complaintId){
        if(usernameDest.equals(""))
            return;
        Complaint complaint = complaintDB.getByID(complaintId);
        complaintDB.editConfirmation("yes", complaintId);
        actionLogger.writeToLog("Complaint approved; " + complaint.toString());
        Warning warning = new Warning(complaintId,usernameDest);
        warningDB.createWarning(warning);
        actionLogger.writeToLog("Warning created; " +warning.toString());
        if (warningDB.checkIf3WarningsAndDeleteThem(usernameDest)){
            User userLower=usersDB.getByUsername(usernameDest);
            //if lowRank() then user become inactive
            if(userLower.lowRank())
                usersDB.updateStatus(usernameDest,"inactive");
        }

    }

}

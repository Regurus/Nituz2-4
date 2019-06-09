package Controller;



import Model.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UsersController{
    public ArrayList<Dispatcher> dispatchers;
    public ArrayList<EmergencyMedicalTechnician> emergencyMedicalTechnicians;
    public ArrayList<Policeman> policemen;
    public ArrayList<Fireman> firemen;


    private static String currentLogin;//should be updated to null on exit
    static UsersDatabase activeConnection;
    public UsersController(){
        if(activeConnection==null)
        {
            activeConnection = new UsersDatabase();
            dispatchers = new ArrayList<Dispatcher>();
            emergencyMedicalTechnicians = new ArrayList<>();
            emergencyMedicalTechnicians = new ArrayList<>();
        }

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

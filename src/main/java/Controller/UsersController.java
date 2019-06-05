package Controller;



import Model.LoginDatabase;

import java.util.regex.Pattern;

public class UsersController{
    private static String currentLogin;//should be updated to null on exit
    static LoginDatabase activeConnection;
    public UsersController(){
        if(activeConnection==null)
        activeConnection = new LoginDatabase();}
    public boolean combinationApprove(String login,String password){
        String[] account = activeConnection.getByLogin(login);
        if(account==null)
            return false;
        if(account[1].contentEquals(password)){
            setCurrentUser(login);
            return true;
        }
        return false;
    }
    protected boolean loginApprove(String login){
        //check with sql
        String[] tuple = activeConnection.getByLogin(login);
        String found = tuple[0];
        return found.equalsIgnoreCase(login);
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

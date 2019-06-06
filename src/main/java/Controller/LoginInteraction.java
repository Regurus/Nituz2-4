package Controller;

import Model.User;
import Model.UsersDatabase;

import java.util.regex.Pattern;

/**
 * basic abstract class for extension by any interface interacting with login details
 */
public abstract class LoginInteraction {
    static UsersDatabase activeConnection;
    protected LoginInteraction(){
        if(activeConnection==null)
            activeConnection = new UsersDatabase();
    }
    protected boolean loginApprove(String login){
        //check with sql
        User tuple = activeConnection.getByUsername(login);
        return tuple!= null;
    }
    public boolean passwordApprove(String password){
        //approves only passwords that include at least 1 number, 1 capital letter and one regular letter.
        return Pattern.matches("[0-9]", password) && Pattern.matches("[a-z]", password) && Pattern.matches("[A-Z]", password);
    }
    public User getUserInfo(String login){
        return activeConnection.getByUsername(login);
    }
    public void endSession(){
        activeConnection.closeConnection();
    }
    public boolean combinationApprove(String login,String password){
        User account = activeConnection.getByUsername(login);
        if(account==null)
            return false;
        return account.getPassword()==password;
    }
}

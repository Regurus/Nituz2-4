package View;

import Controller.UsersController;
import Model.ComplaintDatabase;
import Model.UsersDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static Controller.UsersController.UsersControllerInstance;


public class LoginController extends windowController {
    public static UsersController usersController = UsersControllerInstance(new UsersDatabase(),new ComplaintDatabase());
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public void enter(){
        //TODO add login credentials logic
        this.openNewWindowAndCloseOld("Emer-Agency Main","MainUI.fxml",900,700);
    }
}

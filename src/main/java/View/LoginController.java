package View;

import Controller.UsersController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController extends windowController {
    public static UsersController usersController = new UsersController();
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

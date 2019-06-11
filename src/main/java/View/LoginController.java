package View;

import Controller.UsersController;
import Model.ComplaintDatabase;
import Model.UsersDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static Controller.UsersController.UsersControllerInstance;


public class LoginController extends windowController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label msg;

    @FXML
    public void enter(){
        String login = this.username.getText();
        String pass = this.password.getText();
        UsersController controller = UsersController.UsersControllerInstance();
        boolean logged = controller.approveLogIn(login,pass);
        if(!logged){
            this.msg.setText("Wrong username/password");
            return;
        }
        MainUIController.currentUser = login;

        this.openNewWindowAndCloseOld("Emer-Agency Main","MainUI.fxml",900,700);
    }
}

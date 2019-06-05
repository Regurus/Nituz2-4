package View;

import Controller.UsersController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;


public class LoginController extends windowController {
    public static UsersController usersController = new UsersController();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public void close(){
        usersController.endSession();
        this.username.getScene().getWindow().fireEvent(new WindowEvent(this.username.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}

package View;

import Controller.UsersController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.WindowEvent;

public class MainUIController extends windowController {
    private int depressedBtn = 0;
    @FXML
    private Label username;
    @FXML
    private FontAwesomeIconView personalInfo;
    @FXML
    private MaterialDesignIconView adminFunctions;
    @FXML
    private MaterialDesignIconView complaint;
    @FXML
    private FontAwesomeIconView add;
    @FXML
    private FontAwesomeIconView search;

    @FXML
    public void close(){
        UsersController.endSession();
        this.username.getScene().getWindow().fireEvent(new WindowEvent(this.username.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    @FXML
    private void handleMenuClick(MouseEvent actionEvent){
        int newButton=0;
        if (actionEvent.getSource() == personalInfo) {
            //home_scr.toFront();
            newButton=0;
        }
        if (actionEvent.getSource() == adminFunctions) {
            //add_scr.toFront();
            newButton=1;
        }
        if (actionEvent.getSource() == complaint) {
            //published_scr.toFront();
            newButton=2;
        }
        if (actionEvent.getSource() == add) {
            //favorites_scr.toFront();
            newButton=3;
        }
        if (actionEvent.getSource() == search) {
            //favorites_scr.toFront();
            newButton=4;
        }
        this.updateMenu(newButton);
    }
    private void updateMenu(int newActiveButton){
        String inactiveColor = "#FFFFFF";
        switch (this.depressedBtn){
            case 1:
                this.personalInfo.setFill(Paint.valueOf(inactiveColor));
                break;
            case 2:
                this.add.setFill(Paint.valueOf(inactiveColor));
                break;
            case 3:
                this.complaint.setFill(Paint.valueOf(inactiveColor));
                break;
            case 4:
                this.adminFunctions.setFill(Paint.valueOf(inactiveColor));
                break;
            case 5:
                this.search.setFill(Paint.valueOf(inactiveColor));
                break;
        }

        String activeColor = "#FFFFFF";
        switch (newActiveButton){
            case 1:
                this.personalInfo.setFill(Paint.valueOf(activeColor));
                break;
            case 2:
                this.add.setFill(Paint.valueOf(inactiveColor));
                break;
            case 3:
                this.complaint.setFill(Paint.valueOf(inactiveColor));
                break;
            case 4:
                this.adminFunctions.setFill(Paint.valueOf(inactiveColor));
                break;
            case 5:
                this.search.setFill(Paint.valueOf(inactiveColor));
                break;
        }
        this.depressedBtn = newActiveButton;
    }
}

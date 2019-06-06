package View;

import Controller.UsersController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.WindowEvent;

public class MainUIController extends windowController {
    private int depressedBtn = 4;
    //<editor-fold desc="Icons">

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

    //</editor-fold>

    //<editor-fold desc="Screens">

    @FXML
    private GridPane search_scr;
    @FXML
    private GridPane add_scr;
    @FXML
    private GridPane complaint_scr;
    @FXML
    private GridPane admin_scr;
    @FXML
    private GridPane user_scr;

    //</editor-fold>

    //<editor-fold desc="Complaint Screen">
    @FXML
    private TextField compl_username;
    @FXML
    private ComboBox compl_department;
    @FXML
    private TextArea compl_msg;
    //</editor-fold>

    public void initialize() {
        this.updateMenu(this.depressedBtn);
        search_scr.toFront();
        this.init_cpl();
    }
    private void init_cpl(){
        this.compl_department.getItems().addAll("Fire Department","Police Department","Medical Department","Dispatcher");
    }
    @FXML
    public void close(){
        //UsersController.endSession();
        this.search.getScene().getWindow().fireEvent(new WindowEvent(this.search.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    @FXML
    private void handleMenuClick(MouseEvent actionEvent){
        int newButton=0;
        if (actionEvent.getSource() == personalInfo) {
            user_scr.toFront();
            newButton=0;
        }
        if (actionEvent.getSource() == adminFunctions) {
            admin_scr.toFront();
            newButton=1;
        }
        if (actionEvent.getSource() == complaint) {
            complaint_scr.toFront();
            newButton=2;
        }
        if (actionEvent.getSource() == add) {
            add_scr.toFront();
            newButton=3;
        }
        if (actionEvent.getSource() == search) {
            search_scr.toFront();
            newButton=4;
        }
        this.updateMenu(newButton);
    }
    private void setFill(int index,String color){
        switch (index){
            case 0:
                this.personalInfo.setFill(Paint.valueOf(color));
                break;
            case 1:
                this.adminFunctions.setFill(Paint.valueOf(color));
                break;
            case 2:
                this.complaint.setFill(Paint.valueOf(color));
                break;
            case 3:
                this.add.setFill(Paint.valueOf(color));
                break;
            case 4:
                this.search.setFill(Paint.valueOf(color));
                break;
        }
    }
    private void updateMenu(int newActiveButton){
        String inactiveColor = "#000000";
        String activeColor = "#AAAAAA";
        this.setFill(this.depressedBtn,inactiveColor);
        this.setFill(newActiveButton,activeColor);
        this.depressedBtn = newActiveButton;
    }
    
}

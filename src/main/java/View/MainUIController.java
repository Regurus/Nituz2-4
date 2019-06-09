package View;

import Controller.EASystem;
import Controller.UsersController;
import Model.Complaint;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.WindowEvent;

public class MainUIController extends windowController {
    private int depressedBtn = 4;
    public static Complaint selected;
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
    @FXML
    private Label compl_err_msg;
    //</editor-fold>

    //<editor-fold desc="Admin Screen">
    @FXML
    private ListView adm_scr_cpl_list;
    //</editor-fold>

    //<editor-fold desc="User Screen">
    @FXML
    private Label username;
    @FXML
    private Label division;
    @FXML
    private Label name;
    @FXML
    private Label rank;
    @FXML
    private Label status;
    @FXML
    private Label perm_lvl;
    //</editor-fold>
    public void initialize() {
        this.updateMenu(this.depressedBtn);
        search_scr.toFront();
        this.init_cpl();
        this.init_adm();
        this.init_user();
    }
    private void init_cpl(){
        this.compl_department.getItems().addAll("Fire Department","Police Department","Medical Department","Dispatcher");
    }
    private void init_adm(){
        adm_scr_cpl_list.getItems().addAll("c1","c2","c3");
        adm_scr_cpl_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    private void init_user(){
        this.username.setText("Test User");
        this.division.setText("QA :(");
        this.name.setText("Developer");
        this.rank.setText("Generalisimus");
        this.status.setText("Active");
        this.perm_lvl.setText("Admin");
    }
    @FXML
    private void openAdmComplaintDialog(MouseEvent mouseEvent){
        if(mouseEvent.getClickCount()==2){
            String selected = (String)((ListView)mouseEvent.getSource()).getSelectionModel().getSelectedItems().get(0);
            //get System instance
            //get Complaint Object
            MainUIController.selected = new Complaint("me","you","f you","today");
            this.openNewWindow("Review Complaint","ComplaintDialog.fxml",600,400);
        }
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
    @FXML
    private void sendComplaint(){
        this.compl_err_msg.setText("");
        boolean flag = false;
        if(this.compl_username.getText().length()==0){
            this.compl_err_msg.setText(this.compl_err_msg.getText()+"Please insert user\n");
            flag = true;
        }
        if(this.compl_department.getValue()==null){
            this.compl_err_msg.setText(this.compl_err_msg.getText()+"Please select division\n");
            flag = true;
        }
        if(this.compl_msg.getText().length()==0){
            this.compl_err_msg.setText(this.compl_err_msg.getText()+"Please describe the reason\n");
            flag = true;
        }
        if(flag)
            return;
        //EASystem sys = new EASystem();//TODO change this
        //sys.createNewComplaint(this.compl_username.getText(),(String)this.compl_department.getValue(),this.compl_msg.getText());
    }
    @FXML
    private void test(MouseEvent event){
        System.out.println(event.getClickCount());
    }
}

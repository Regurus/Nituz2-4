package View;

import Controller.EASystem;
import Model.AdminUser;
import Model.Complaint;
import Controller.UsersController;

import Model.User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class MainUIController extends windowController {
    private int depressedBtn = 4;
    private boolean isAdmin = true;
    private static int activeComplaint = -1;
    public static String currentUser;

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

    //<editor-fold desc="Menu">
    @FXML
    private GridPane admin_blck;
    @FXML
    private GridPane createC_blck;
    @FXML
    private TilePane menu;
    //</editor-fold>


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
    @FXML
    private TextField adm_new_cat;
    @FXML
    private Label adm_new_cat_err;
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
        this.init_user();
        if(!this.isAdmin){
            this.menu.getChildren().remove(this.admin_blck);
        }
        else{
            this.menu.getChildren().remove(this.createC_blck);
        }
        this.init_adm();
    }
    private void init_adm(){
        if(!this.isAdmin){

            return;
        }
        ArrayList<Complaint> list = UsersController.UsersControllerInstance().getAllComplaints(EASystem.eaSystemInstance().getAdmin().getDivision());
        if(list==null)
            return;
        ArrayList<String> values = new ArrayList<String>();
        for(Complaint c :list){
            values.add(c.getId()+" :: "+c.getDestination());
        }
        adm_scr_cpl_list.getItems().addAll(values.toArray());
        adm_scr_cpl_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    private void init_user(){
        if(UsersController.UsersControllerInstance().getLoginUser()!=null){
            this.isAdmin = false;
            User active = UsersController.UsersControllerInstance().getLoginUser();
            this.username.setText(active.getUserName());
            this.division.setText(active.getType());
            this.name.setText(active.getName());
            this.rank.setText(active.getRank());
            this.status.setText(active.getStatus());
            this.perm_lvl.setText("User");
        }
        else{
            this.isAdmin = true;
            AdminUser admin =  EASystem.eaSystemInstance().getAdmin();
            this.username.setText(admin.getUsername());
            this.division.setText(admin.getDivision());
            this.name.setText(admin.getName());
            this.rank.setText("N/A");
            this.status.setText("Active");
            this.perm_lvl.setText("Admin");
        }
    }
    @FXML
    private void openAdmComplaintDialog(MouseEvent mouseEvent){
        if(mouseEvent.getClickCount()==2){
            String selected = (String)((ListView)mouseEvent.getSource()).getSelectionModel().getSelectedItems().get(0);
            activeComplaint = Integer.parseInt(selected.split(Pattern.quote(" :: "))[0]);
            //get System instance
            //get Complaint Object
            //MainUIController.selected = new Complaint("me","you","f you","today");
            this.openNewWindow("Review Complaint","ComplaintDialog.fxml",600,400);
        }
    }
    public Complaint getSelectedComplaint(){
        ArrayList<Complaint> list = UsersController.UsersControllerInstance().getAllComplaints(EASystem.eaSystemInstance().getAdmin().getDivision());
        for(Complaint c : list){
            if(c.getId()==activeComplaint)
                return c;
        }
        return null;
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
        this.compl_err_msg.setTextFill(Color.RED);
        boolean flag = false;
        if(this.compl_msg.getText().length()==0){
            this.compl_err_msg.setText(this.compl_err_msg.getText()+"Please insert user\n");
            flag = true;
        }
        if(this.compl_msg.getText().length()==0){
            this.compl_err_msg.setText(this.compl_err_msg.getText()+"Please describe the reason\n");
            flag = true;
        }
        if(flag)
            return;
        UsersController sys =UsersController.UsersControllerInstance();
        boolean res = sys.createNewComplaint(this.compl_username.getText(),this.compl_msg.getText());
        if(!res){
            this.compl_err_msg.setText(this.compl_err_msg.getText()+"Creation Error!!!\n");
            return;
        }
        this.compl_err_msg.setTextFill(Color.GREEN);
        this.compl_err_msg.setText("Done!");
        this.compl_msg.setText("");
    }
    public void test(){
        System.out.println("TTTTTTT");
    }
    @FXML
    private void createCategory(){
        String catValue = adm_new_cat.getText();
        EASystem controller = EASystem.eaSystemInstance();
        boolean result = controller.createNewCategory(catValue);
        if(!result){
            adm_new_cat_err.setTextFill(Color.RED);
            adm_new_cat_err.setText(catValue+": Category Exist!");
        }
        else{
            adm_new_cat_err.setTextFill(Color.GREEN);
            adm_new_cat_err.setText(catValue+": OK!");
        }
    }
}


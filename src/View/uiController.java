package View;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import Controller.*;
import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;


public class uiController extends windowController {

    public static uiController Ui;

    //<editor-fold desc="Settings Controls">
    @FXML
    private TextField username;
    @FXML
    private PasswordField opassword;
    @FXML
    private PasswordField npassword;
    @FXML
    private PasswordField cpassword;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField city;
    @FXML
    private Text msg;
    @FXML
    private AnchorPane user_edit_pane;
    @FXML
    private Label username_lbl;
    //</editor-fold>

    //<editor-fold desc="Details">
    @FXML
    private Label details_dest_lbl;
    @FXML
    private Label details_start_lbl;
    @FXML
    private Label details_end_lbl;
    @FXML
    private Label details_price_lbl;
    @FXML
    private TextArea details_desc_area;
    @FXML
    private ImageView details_img;
    //</editor-fold>


    //<editor-fold desc="Menu">
    @FXML
    private Button home_btn;
    @FXML
    private Button add_btn;
    @FXML
    private Button published_btn;
    @FXML
    private Button favorites_btn;
    //</editor-fold>

    //<editor-fold desc="Screens">
    @FXML
    private TilePane home_scr_items;
    @FXML
    private AnchorPane home_scr;
    @FXML
    private AnchorPane details_scr;
    @FXML
    private GridPane add_scr;
    @FXML
    private AnchorPane favorites_scr;
    //</editor-fold>

    //<editor-fold desc="Published">
    @FXML
    private TilePane published_scr_items;
    @FXML
    private AnchorPane published_scr;
    //</editor-fold>

    //<editor-fold desc="Favorites">
    @FXML
    private TilePane favorites_scr_items;

    //</editor-fold>

    //<editor-fold desc="Add Vacation Screen">
    @FXML
    private TextField add_text_region;
    @FXML
    private TextField add_text_city;
    @FXML
    private TextField add_text_price;
    @FXML
    private DatePicker add_date_start;
    @FXML
    private DatePicker add_date_end;
    @FXML
    private TextArea add_text_description;
    @FXML
    private ImageView add_image_preview;

    //</editor-fold>

    //<editor-fold desc="Search">
    @FXML
    private DatePicker searchDate;
    @FXML
    private TextField searchBox;
    //</editor-fold>

    //<editor-fold desc="Messages">
    @FXML
    private TilePane msg_controls;
    @FXML
    private Label add_msg;
    @FXML
    private AnchorPane msg_scr;
    @FXML
    private AnchorPane msg_content;
    //</editor-fold>

    public void initialize(){
        uiController.Ui = this;
        this.initializeInterfaces();
        initializePublished();
        initializeHomeScreen();
        initializePurchases();
        initializeMessages();
        //scrollPane.setContent(this.test_container);
    }
    private void initializeInterfaces(){

    }
    private void initializeHomeScreen(){

    }
    private void initializePublished(){

    }
    public void initializeUserData(){

    }
    private void initializeMessages(){

    }
    @FXML
    private void initializePurchases(){

    }
    @FXML
    private void handleUpdate() {

    }
    @FXML
    private void handleMenuClick(ActionEvent actionEvent){
        int newButton=0;
        if (actionEvent.getSource() == home_btn) {
            home_scr.toFront();
            newButton=0;
        }
        if (actionEvent.getSource() == add_btn) {
            add_scr.toFront();
            newButton=1;
        }
        if (actionEvent.getSource() == published_btn) {
            published_scr.toFront();
            newButton=2;
        }
        if (actionEvent.getSource() == favorites_btn) {
            favorites_scr.toFront();
            newButton=3;
        }
        this.updateMenu(newButton);
    }
    @FXML
    private void handleSettingsClick(){
        user_edit_pane.toFront();
    }
    private void handleMessagesClick(Button pressed){

    }
    @FXML
    private void handleLogoutClick(){
        UsersController.nullifyCurrentUser();
        this.openNewWindowAndCloseOld("Vaction4U","/signIn.fxml",600, 400);
        this.home_btn.getScene().getWindow().fireEvent(new WindowEvent(this.home_btn.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    @FXML
    private void handleClose(){
        this.home_btn.getScene().getWindow().fireEvent(new WindowEvent(this.home_btn.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    @FXML
    private void handleOpenImage(){

    }
    @FXML
    private void handleSearch(){

    }
    @FXML
    private void handlePublishNewVacation(ActionEvent actionEvent) {

    }
    @FXML
    private void handleApplication(){

    }
    @FXML
    private void handleOpenMSG(){
        this.msg_scr.toFront();
    }
    private void openMessageSession(String with){

    }
    private void updateMenu(int newActiveButton){

    }

    private void addItems(String name,TilePane addTo){

    }




}

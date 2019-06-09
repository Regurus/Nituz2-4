package View;


import Model.Complaint;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ComplaintDialogController extends windowController{
    @FXML
    private Label from;
    @FXML
    private Label about;
    @FXML
    private Label date;
    @FXML
    private TextArea descr;
    public void initialize(){
        Complaint active = MainUIController.selected;
        this.from.setText("From: "+active.getSource());
        this.about.setText("About: "+active.getDestination());
        this.date.setText("Date: "+active.getDate());
        this.descr.setText(active.getDescription());
    }
    @FXML
    private void acceptComplaint(){
        System.out.println("Accepted");
    }
    @FXML
    private void rejectComplaint(){
        System.out.println("Rejected");
    }
}

package View;


import Model.Complaint;
import Controller.EASystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class ComplaintDialogController extends windowController{
    @FXML
    private Label from;
    @FXML
    private Label about;
    @FXML
    private Label date;
    @FXML
    private TextArea descr;
    private Complaint complaint;
    public void initialize(){
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("MainUI.fxml"));
            Parent root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainUIController controller = loader.getController();
        this.complaint = controller.getSelectedComplaint();
        this.from.setText("From: "+this.complaint.getSource());
        this.about.setText("About: "+this.complaint.getDestination());
        this.date.setText("Date: "+this.complaint.getDate());
        this.descr.setText(this.complaint.getDescription());
    }

    @FXML
    private void acceptComplaint(){
        EASystem.eaSystemInstance().createNewWarning(this.complaint.getDestination(),this.complaint.getId());
        System.out.println("Accepted");
    }
    @FXML
    private void rejectComplaint(){
        EASystem.eaSystemInstance().declineComplaint(this.complaint.getId());
        System.out.println("Rejected");
    }
}

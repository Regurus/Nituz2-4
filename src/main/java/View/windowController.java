package View;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class windowController {
    private Stage stage;
    protected final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
    protected final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    @FXML
    protected Node close;
    protected void openNewWindow(String windowName, String fxmlFile, int width, int height){
        try {
            this.stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle(windowName);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource(fxmlFile));
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.showAndWait();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    void openNewWindowAndCloseOld(String windowName, String fxmlFile, int width, int height){
        this.close();
        try {
            this.stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle(windowName);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource(fxmlFile));
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void minimize(){
        Stage s = (Stage)this.close.getScene().getWindow();
        s.setIconified(true);
    }
    @FXML
    public void close(){
        //usersController.endSession();
        ((Stage)this.close.getScene().getWindow()).close();
    }

}

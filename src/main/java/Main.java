

import Model.Complaint;
import Model.ComplaintDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


public class Main extends Application {
    /***
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        primaryStage.setTitle("Emer-Agency");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);


    }
}

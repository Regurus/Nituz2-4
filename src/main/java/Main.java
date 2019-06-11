

import Controller.UsersController;
import Model.Complaint;
import Model.ComplaintDatabase;
import Model.Warning;
import Model.WarningDatabase;
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




        /*ComplaintDatabase cd = new ComplaintDatabase();
        System.out.println(cd.getLastIndex());

        /*Warning w = new Warning(1,"test", 1);
        WarningDatabase wd = new WarningDatabase();
        wd.createWarning(w);
        Warning wa = wd.getWarningByField("id", "1");*/

    }
}

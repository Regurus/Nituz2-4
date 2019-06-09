
import Model.CategoriesDatabase;
import Model.UsersDatabase;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


public class Main extends Application {
    /***
     * currently no UI refresh on action for UI to refresh individual screens have to perform log-out log-in cycle
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainUI.fxml"));
        //root.getStylesheets().add(getClass().getResource("Main_Style.css").toString());
        primaryStage.setTitle("Emer-Agency");
        primaryStage.setScene(new Scene(root, 900, 700));
        //SetStageCloseEvent(primaryStage);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();

    }
    public static void main(String[] args) {
        //deployment check
        //if not deployed do deployment procedure
        //initialize database connections
        //open Login UI
        launch(args);


    }
}

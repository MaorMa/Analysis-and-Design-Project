package Controller;

import Model.Model;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Vacations.fxml"));
        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 1250  , 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}

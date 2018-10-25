package View;

import Controller.AController;
import Controller.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeView implements Initializable{
    @FXML
    public javafx.scene.control.Label currentuser;

    public void setTitle(){
        currentuser.setText("Welcome " + AController.getCurrentUser());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle();
    }

    public void update(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../View/Update.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 600, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setTitle("Create User");
        stage.setScene(scene);
        stage.show();
    }
}

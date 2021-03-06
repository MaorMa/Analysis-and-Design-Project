package View;

import Controller.AController;
import Controller.HomeController;
import com.sun.javafx.stage.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeView extends AView  implements Initializable{
    @FXML
    public javafx.scene.control.Label currentuser;
    public javafx.scene.control.Button deleteButton;


    private HomeController homeController = new HomeController();

    public void setTitle(){
        currentuser.setText("Welcome " + AController.getCurrentUser() + "!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle();
    }

    public void update(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Update.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 900, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setTitle("Update User");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void delete(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Delete.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 900, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setTitle("Delete User");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void read(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Read.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 900, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setTitle("Read User");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

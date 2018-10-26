package View;

import Controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import Model.Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements Initializable {

    @FXML
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.image.ImageView loading;

    private LoginController loginController = new LoginController();

    public void CheckifValid(ActionEvent actionEvent) throws InterruptedException {
        // delay & exit on other thread
        new Thread(() -> {
            try {
                loading.setVisible(true);
                Thread.sleep(4000);
                loading.setVisible(false);
            } catch (InterruptedException ex) {
            }
        }).start();

        boolean valid = loginController.checkValidUser(username.getText(),password.getText());
        if(valid){
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Home.fxml"));
            Scene scene=null;
            try{
                scene=new Scene(fxmlLoader.load(), 900, 600);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage=new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);
            Thread.sleep(1200);
            stage.setResizable(false);
            stage.show();
        }
        else {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Error while SignIn");
            alert.setContentText("Bad password or username");
            alert.showAndWait();        }
    }

    public void CreateUser(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Create.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 900, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Create User");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loading.setVisible(false);
    }
}

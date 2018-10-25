package View;

import Controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    public void CheckifValid(ActionEvent actionEvent) {
        //loading.setVisible(true);
        boolean valid = loginController.checkValidUser(username.getText(),password.getText());
        if(valid){
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../View/Home.fxml"));
            Scene scene=null;
            try{
                scene=new Scene(fxmlLoader.load(), 600, 600);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage=new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("bad username or password");
        }
    }

    public void CreateUser(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../View/Create.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 600, 600);
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

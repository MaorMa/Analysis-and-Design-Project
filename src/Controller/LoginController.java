package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Model;

import java.io.IOException;

public class LoginController {

    private Model myModel;
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;

    public void CheckifValid(ActionEvent actionEvent) {
        myModel = new Model();
        boolean valid = myModel.checkValidUser(username.getText(),password.getText());
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

    public void CreateUser(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../View/Create.fxml"));
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

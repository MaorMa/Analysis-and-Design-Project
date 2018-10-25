package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements Initializable {

    private Model myModel;
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.image.ImageView loading;


    public void CheckifValid(ActionEvent actionEvent) {
        loading.setVisible(true);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loading.setVisible(false);
    }
}

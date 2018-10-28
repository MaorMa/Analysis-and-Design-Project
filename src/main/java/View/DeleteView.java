package View;

import Controller.DeleteController;
import Controller.HomeController;
import com.sun.javafx.stage.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Maor on 10/25/2018.
 */
public class DeleteView extends AView implements Initializable {
    @FXML
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField Cpassword;
    public javafx.scene.control.CheckBox confirm;

    public javafx.scene.control.Button deleteUserButton;

    DeleteController deleteController = new DeleteController();

    public void deleteUser() {
        if(password.getText().equals("") || Cpassword.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Error");
            alert.setHeaderText("One of the fields was left empty");
            alert.setContentText("Please make sure you fill in both password fields in order to delete your user");

            alert.showAndWait();
        }
        else if(!confirm.isSelected()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Error");
            alert.setHeaderText("The confirmation checkbox was not pressed");
            alert.setContentText("Please make sure you check the checkbox in order to delete your user");

            alert.showAndWait();
        }
        else if(!password.getText().equals(Cpassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Error");
            alert.setHeaderText("Passwords did not match");
            alert.setContentText("Please make sure both passwords are similar in order to delete your user");

            alert.showAndWait();
        }
        else{
            deleteController.deleteUser(username.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("User Removed");
            alert.setContentText("You will be signed out automatically");


            alert.showAndWait();
            Stage stage = (Stage) deleteUserButton.getScene().getWindow();
            stage.close();
            closeStage("Home");

        }
    }

    private void setCurrentUserDetails(){
        username.setText(deleteController.getUser().getUsername());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCurrentUserDetails();
    }

    public void onEnter(KeyEvent keyEvent) throws InterruptedException {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            Thread.sleep(500);
            this.deleteUser();
        }
    }
}

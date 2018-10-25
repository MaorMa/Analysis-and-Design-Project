package View;

import Controller.DeleteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by Maor on 10/25/2018.
 */
public class DeleteView implements Initializable{
    @FXML
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField Cpassword;
    public javafx.scene.control.CheckBox confirm;

    public javafx.scene.control.Button deleteUserButton;



    DeleteController deleteController = new DeleteController();

    public void deleteUser(ActionEvent actionEvent) {
        if(password.getText().equals(Cpassword.getText()) && confirm.isSelected()) {
            deleteController.deleteUser(username.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("User Removed");

            alert.showAndWait();
            Stage stage = (Stage) deleteUserButton.getScene().getWindow();
            stage.close();
        }
    }

    private void setCurrentUserDetails(){
        username.setText(deleteController.getUser().getUsername());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCurrentUserDetails();
    }
}

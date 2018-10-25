package View;

import Controller.CreateController;
import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

/**
 * Created by Maor on 10/24/2018.
 */
public class CreateView {
    private CreateController createController=new CreateController();
    @FXML
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField fname;
    public javafx.scene.control.TextField lname;
    public javafx.scene.control.DatePicker date;
    public javafx.scene.control.TextField city;

    public javafx.scene.control.Button createUserButton;

    public void createUser(){
        String username_to_string = username.getText();
        String password_to_string = password.getText();
        String fname_to_string = fname.getText();
        String lname_to_string = lname.getText();
        String city_to_string = city.getText();
        String date_to_string;

        if(date.getValue() != null)//check if date is null
        {
            date_to_string = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//problem when empty!
        }
        else {
            date_to_string = "";//if date is null - set to empty'
        }

        if(username_to_string.isEmpty() || password_to_string.isEmpty() || fname_to_string.isEmpty() ||
                lname_to_string.isEmpty() || date_to_string.isEmpty() || city_to_string.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Error while filling in the form");
            alert.setContentText("One of the fields was left blank");

            alert.showAndWait();
        }
        else {
            Boolean added = createController.insert_form(username_to_string, password_to_string, fname_to_string, lname_to_string
                    , date_to_string, city_to_string);
            if(added) {
                Stage stage = (Stage) createUserButton.getScene().getWindow();
                stage.close();
            }
        }
    }
}

package View;

import Controller.AController;
import Controller.UpdateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by Maor on 10/25/2018.
 */
public class UpdateView implements Initializable{
    private UpdateController updateController=new UpdateController();
    @FXML
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField fname;
    public javafx.scene.control.TextField lname;
    public javafx.scene.control.DatePicker date;
    public javafx.scene.control.TextField city;

    public javafx.scene.control.Button updateUserButton;
    
    /**
     * Set current details you signed in with\changed last time
     */
    private void setCurrentUserDetails(){
        updateController.getUser();
        username.setText(updateController.getUser().getUsername());
        password.setText(updateController.getUser().getPassword());
        fname.setText(updateController.getUser().getFirst_name());
        lname.setText(updateController.getUser().getLast_name());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(updateController.getUser().getBdate() , formatter);
        date.setValue(localDate);
        city.setText(updateController.getUser().getCity());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCurrentUserDetails();
    }

    public void updateUserInfo(ActionEvent actionEvent) {
        String date_to_string = "";
        String oldPassword = updateController.getUser().getPassword();//todo need to check what if pw is empty!!!
        if(date.getValue() != null)//check if date is null
        {
            date_to_string = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//problem when empty!
        }
        updateController.updateUserInfo(username.getText(), password.getText(), date_to_string, fname.getText(),
                lname.getText(), city.getText());
    }
}

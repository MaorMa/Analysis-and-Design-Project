package View;

import Controller.ReadController;
import Model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Maor on 10/25/2018.
 */
public class ReadView {

    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField fname;
    public javafx.scene.control.TextField lname;
    public javafx.scene.control.DatePicker date;
    public javafx.scene.control.TextField city;

    public javafx.scene.control.Button closeButton;

    private ReadController readController=new ReadController();

    public void readUserInfo(ActionEvent actionEvent) {
        User user = readController.getUserByUsername(username.getText());
        if(user == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Error while Read user details");
            alert.setContentText("invalid user");
            alert.showAndWait();
        }else{
            fname.setText(user.getFirst_name());
            lname.setText(user.getLast_name());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if(!readController.checkIfExists(user.getUsername()) || user.getBdate() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Error while Read user details");
                alert.setContentText("invalid user");
            }
            else {
                LocalDate localDate = LocalDate.parse(user.getBdate(), formatter);
                date.setValue(localDate);
                city.setText(user.getCity());
            }
        }

    }

//    public void closeStage(ActionEvent actionEvent) {
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
//    }
}

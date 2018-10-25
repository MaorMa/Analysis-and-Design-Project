package View;

import Controller.UpdateController;
import javafx.fxml.FXML;

/**
 * Created by Maor on 10/25/2018.
 */
public class UpdateView {
    private UpdateController createController=new UpdateController();
    @FXML
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField fname;
    public javafx.scene.control.TextField lname;
    public javafx.scene.control.DatePicker date;
    public javafx.scene.control.TextField city;

    public javafx.scene.control.Button updateUserButton;

    public void updateUser() {

    }
}

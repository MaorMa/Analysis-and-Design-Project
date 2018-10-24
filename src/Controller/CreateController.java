package Controller;

import Model.Model;
import javafx.fxml.FXML;
import java.time.format.DateTimeFormatter;

/**
 * Created by Maor on 10/24/2018.
 */
public class CreateController {
    Model mymodel;

    @FXML
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField fname;
    public javafx.scene.control.TextField lname;
    public javafx.scene.control.DatePicker date;
    public javafx.scene.control.TextField city;

    public void createUser(){
        mymodel = new Model();
        String username_to_string = username.getText();
        String password_to_string = password.getText();
        String fname_to_string = fname.getText();
        String lname_to_string = lname.getText();
        String date_to_string = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String city_to_string = city.getText();
        mymodel.insert_form(username_to_string,password_to_string,fname_to_string,lname_to_string
        ,date_to_string,city_to_string);
    }
}

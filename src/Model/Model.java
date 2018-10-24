package Model;

import Controller.CreateController;
import Controller.DBconnection;
import javafx.scene.control.*;

/**
 * Created by Maor on 10/24/2018.
 */
public class Model {
    private DBconnection dBconnection;
    private CreateController create;
    public Model(){
        dBconnection = new DBconnection();
    }

    public void insert_form(String username_to_string, String password_to_string, String fname_to_string, String lname_to_string, String date_to_string, String city_to_string){
        User user = new User(username_to_string, password_to_string, fname_to_string, lname_to_string, date_to_string, city_to_string);
        boolean done = dBconnection.insertUser(user);
        if(!done){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Username taken");
            alert.setContentText("\"" +username_to_string + "\" This username is already taken. Try a diffenet one!");

            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Welcome!");
            alert.setHeaderText("User Created!");
            alert.setContentText("Welcome \"" +username_to_string + "\" to Vacation4U!");

            alert.showAndWait();
        }
    }
}

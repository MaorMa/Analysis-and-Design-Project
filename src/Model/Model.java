package Model;

import Controller.CreateController;
import Controller.DBconnection;

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
        dBconnection.insertUser(user);
    }
}

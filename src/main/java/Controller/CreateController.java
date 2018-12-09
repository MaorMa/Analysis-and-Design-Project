package Controller;
import Model.*;

public class CreateController extends AController {
    //private Model mymodel=new Model();

    public boolean insert_form(String username_to_string, String password_to_string, String fname_to_string,
                               String lname_to_string, String date_to_string, String city_to_string){
        return myModel.insert_form(username_to_string, password_to_string, fname_to_string, lname_to_string,
                date_to_string, city_to_string);
    }
}

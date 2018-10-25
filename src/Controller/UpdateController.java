package Controller;

import Model.*;

/**
 * Created by Maor on 10/25/2018.
 */
public class UpdateController extends AController{

    public User getUser(){
        return myModel.getUserInfo(myModel.getCurrentuser());
    }

    public void updateUserInfo(String username_to_string, String password_to_string, String fname_to_string,
                               String lname_to_string, String date_to_string, String city_to_string){
        User newUser = new User(username_to_string,password_to_string,fname_to_string,
                lname_to_string, date_to_string, city_to_string);

        myModel.updateUserInfo(myModel.getCurrentuser(),newUser);

    }
}

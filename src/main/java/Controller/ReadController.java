package Controller;

import Model.User;

/**
 * Created by Maor on 10/25/2018.
 */
public class ReadController extends AController{
    public User getUser(String username){
        return myModel.getUserInfo(username);
    }

    public boolean checkifExists(String username) {
        if(myModel.checkifExists(username) == true) {
            return true;
        }else
            return false;
    }
}

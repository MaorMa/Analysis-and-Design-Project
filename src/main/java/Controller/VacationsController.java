package Controller;
import Model.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VacationsController extends AController{
    public HashMap<Integer, Vacation> getVacations(){
        return myModel.getVacations();
    }
    public void logout() {
        myModel.setCurrent_user(null);
    }
}

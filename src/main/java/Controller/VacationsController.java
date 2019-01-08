package Controller;
import Model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VacationsController extends AController{
    public HashMap<Integer, Vacation> getVacations(){
        return myModel.getVacations();
    }
    public void logout() {
        myModel.setCurrent_user(null);
    }

    public boolean checkOffers(){
        if(myModel.receiveOffers().size() >0){
            return true;
        }
        return false;
    }

    public Map<Trade,Boolean> getTradesUpdate(String currentUser) {
        return myModel.getTradesUpdate(currentUser);
    }
}

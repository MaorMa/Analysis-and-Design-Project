package Controller;

import Model.Vacation;

public class purchaseVacationController extends AController{
    public String getUsername(){
        return getCurrentUser();
    }

    public void removeVacation(int purchaseNumber){
        myModel.removeVacations(purchaseNumber);
    }
}
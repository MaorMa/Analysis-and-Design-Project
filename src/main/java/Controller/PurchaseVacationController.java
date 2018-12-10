package Controller;

import Model.Vacation;

public class PurchaseVacationController extends AController{
    public String getUsername(){
        return getCurrentUser();
    }

    public boolean confirmPayment(int vacationID, String buyer , String method){
        return myModel.confirmPayment(vacationID,buyer,method);
    }

    public void removeVacation(int purchaseNumber){
        myModel.removeVacations(purchaseNumber);
    }
}
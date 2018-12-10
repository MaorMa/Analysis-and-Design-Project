package Controller;

import Model.Vacation;

public class PublishVacationController extends AController{
    public String getUsername(){
        return getCurrentUser();
    }

    public int addVacation(Vacation vacation){
        return myModel.publishVac(vacation);
    }
}

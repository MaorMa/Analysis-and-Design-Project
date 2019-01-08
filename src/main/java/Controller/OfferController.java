package Controller;

import Model.Trade;
import Model.Vacation;
import View.AView;

import java.util.ArrayList;

public class OfferController extends AController {
    int offerForId = -1;

    //vacations that i want to offer
    public ArrayList<Vacation> getPublishedVacations(int chosen){
      return myModel.getPublishedVacations(chosen);
    }

    public void sendOffer(int offeredId,int id){
        offerForId = id;
        if(offerForId !=-1 && offeredId!=-1){
            myModel.sendOffer(new Trade(myModel.readVacation(offerForId),myModel.readVacation(offeredId)));
        }
    }
/*select OfferedForVacationID, OfferedVacationID
from Trades as T join
                select Vacations.VacationID
                    from Vacations
                    where Vacations.Advertiser='a') as V
on T.OfferedForVacationID=V.VacationID*/
}

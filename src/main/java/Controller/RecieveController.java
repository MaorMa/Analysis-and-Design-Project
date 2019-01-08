package Controller;

import Model.Trade;

import java.util.ArrayList;

public class RecieveController extends AController {

    public ArrayList<Trade> receiveOffers(){
        return myModel.receiveOffers();
    }

    public void sendResponse(boolean flag){
    }

    public void sendResponse(int offered, int offeredFor,boolean ans) {
        myModel.sendResponse(offered,offeredFor,ans);
    }
}

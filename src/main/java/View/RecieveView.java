package View;

import Controller.RecieveController;

import java.net.URL;
import java.util.ResourceBundle;

public class RecieveView extends AView {

    RecieveController recieveController = new RecieveController();

    public void initialize(URL location, ResourceBundle resources) {
        recieveController.receiveOffers();
    }
}

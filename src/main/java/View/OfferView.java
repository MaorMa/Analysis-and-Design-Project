package View;

import Controller.LoginController;
import Controller.OfferController;

import java.net.URL;
import java.util.ResourceBundle;

public class OfferView extends AView {

    private OfferController offerController = new OfferController();

    public void initialize(URL location, ResourceBundle resources) {
        offerController.getPublishedVacations();
    }
}

package View;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maor on 12/9/2018.
 */
public class PurchaseVacationView extends AView implements Initializable {
    public javafx.scene.control.TextField cardNumber;
    public javafx.scene.control.DatePicker expDate;
    public javafx.scene.control.ChoiceBox cardType;
    public javafx.scene.control.TextField CCV;
    public javafx.scene.control.Button purchaseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardType.getItems().addAll("Visa","PayPal");
        cardType.setValue("Visa");
    }


    public void purchaseVac(ActionEvent actionEvent) {
        Stage stage = (Stage) purchaseButton.getScene().getWindow();
        //check conditions and then set
        AView.PaymentApprovement = true;
        stage.close();
    }
}

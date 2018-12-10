package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maor on 12/9/2018.
 */
public class PurchaseVacationView extends AView implements Initializable {
    public javafx.scene.control.ChoiceBox cardType;
    public javafx.scene.control.TextField cardNumberValue;
    public javafx.scene.control.TextField emailValue;
    public javafx.scene.control.DatePicker expDateValue;
    public javafx.scene.control.PasswordField pwValue;
    public javafx.scene.control.TextField CCVvalue;
    public javafx.scene.control.Button purchaseButton;

    public javafx.scene.control.Label cardNumberLabel;
    public javafx.scene.control.Label emailLabel;
    public javafx.scene.control.Label expDateLabel;
    public javafx.scene.control.Label pwLabel;
    public javafx.scene.control.Label ccvLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardType.getItems().addAll("Visa","PayPal");
        cardType.setValue("Visa");
        cardType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(number2.intValue()==1) {
                    //Labels - paypal
                    cardNumberLabel.setVisible(false);
                    emailLabel.setVisible(true);
                    expDateLabel.setVisible(false);
                    pwLabel.setVisible(true);
                    ccvLabel.setVisible(false);

                    emailValue.setVisible(true);
                    pwValue.setVisible(true);
                    cardNumberValue.setVisible(false);
                    expDateValue.setVisible(false);
                    CCVvalue.setVisible(false);
                }
                else{
                    //labels - visa
                    cardNumberLabel.setVisible(true);
                    emailLabel.setVisible(false);
                    expDateLabel.setVisible(true);
                    pwLabel.setVisible(false);
                    ccvLabel.setVisible(true);

                    emailValue.setVisible(false);
                    pwValue.setVisible(false);
                    cardNumberValue.setVisible(true);
                    expDateValue.setVisible(true);
                    CCVvalue.setVisible(true);
                }

            }
        });
    }

    public void purchaseVac(ActionEvent actionEvent) {
        System.out.println(AView.purchaseNumber);
        Stage stage = (Stage) purchaseButton.getScene().getWindow();
        //check conditions and then set
        AView.PaymentApprovement = true;
        stage.close();
    }

    public void changeInfoReq(MouseEvent mouseEvent) {
        System.out.println("relesed");
    }
}

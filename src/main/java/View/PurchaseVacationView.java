package View;

import Controller.PurchaseVacationController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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

    public PurchaseVacationController purchaseVacationController = new PurchaseVacationController();
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
        boolean correct = false;
        //check conditions and then set
        if(cardType.getValue().equals("Visa")){
            correct = checkVisa();
        }
        else{
            correct = checkPP();
        }
        if(correct) {
            purchaseVacationController.confirmPayment(AView.purchaseNumber,purchaseVacationController.getUsername(),cardType.getValue().toString());
            AView.PaymentApprovement = true;
            stage.close();
        }
    }

    //paypal
    private boolean checkPP() {
        String email = emailValue.getText();
        String pw = pwValue.getText();
        if(!email.isEmpty() && email.contains("@") && email.contains(".")){
            if(!pw.isEmpty()){
                return true;
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Password");
                alert.setHeaderText("The password that you've entered is empty");
                alert.setContentText("Enter a valid non-empty password");

                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong E-mail");
            alert.setHeaderText("The email that you've entered is wrong");
            alert.setContentText("Enter a valid email, like this: xxxx@yyyy.zzz");

            alert.showAndWait();
        }
        return false;
    }

    //visa
    private boolean checkVisa() {
        String card = cardNumberValue.getText();
        String ccv = CCVvalue.getText();
        if(!card.isEmpty()) {
            try{
                Integer.parseInt(card);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Bad Card Number");
                alert.setHeaderText("The card number that you've entered is bad");
                alert.setContentText("Enter a valid card number, made up from only numbers");

                alert.showAndWait();
                return false;
            }
            if(!ccv.isEmpty()){
                try{
                    Integer.parseInt(ccv);
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Bad CCV Number");
                    alert.setHeaderText("The CCV number that you've entered is bad");
                    alert.setContentText("Enter a valid CCV number, made up from only numbers");

                    alert.showAndWait();
                    return false;
                }
                if(expDateValue.getValue() != null)//check if date is null
                {
                    return true;
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No Expiration date Number");
                    alert.setHeaderText("You havn't entered Expiration date");
                    alert.setContentText("Enter a valid Expiration date");

                    alert.showAndWait();
                    return false;
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No CCV Number");
                alert.setHeaderText("The CCV number that you've entered is empty");
                alert.setContentText("Enter a valid CCV number, made up from only numbers");

                alert.showAndWait();
                return false;
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Card Number");
            alert.setHeaderText("The card number that you've entered is empty");
            alert.setContentText("Enter a valid card number, made up from only numbers");

            alert.showAndWait();
            return false;
        }
    }

}

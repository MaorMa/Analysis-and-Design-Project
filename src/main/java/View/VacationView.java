package View;

import Controller.AController;
import Controller.VacationsController;
import Model.Trade;
import Model.User;
import Model.Vacation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class VacationView extends AView implements Initializable {

    /**
     * Fields
     */
    private VacationsController vacationsController = new VacationsController();

    @FXML
    public javafx.scene.control.Button loginButton;
    public javafx.scene.control.Button accountButton;
    public javafx.scene.control.Button LogoutButton;
    public javafx.scene.control.Label currentuser;
    public javafx.scene.control.Button offerTradeButton;
    public javafx.scene.control.Button watchOffersButton;


    public void setTitle() {
        currentuser.setText("Hello " + AController.getCurrentUser());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.updateTableView("");
        offerTradeButton.setDisable(true);
        watchOffersButton.setDisable(true);
    }

    @FXML
    public void login() throws InterruptedException {
        scenes.put("VacationView", loginButton.getScene());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1200, 600);
            scenes.put("Login", scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        if(AController.getCurrentUser()!=null) {
            currentuser.setVisible(true);
            accountButton.setDisable(false);
            LogoutButton.setDisable(false);
            loginButton.setDisable(true);
            setTitle();
            offerTradeButton.setDisable(false);
            if(vacationsController.checkOffers()){
                watchOffersButton.setDisable(false);
            }

            Map<Trade,Boolean> TradesUpdate = vacationsController.getTradesUpdate(AController.getCurrentUser());
            for(Map.Entry entry:TradesUpdate.entrySet()){
                Vacation offerId = ((Trade)entry.getKey()).offerId;
                String offerd_id = "Vaction ID : " + offerId.getId() + " From: " + offerId.getAdvertiser();
                Vacation offerForId = ((Trade)entry.getKey()).offerForId;
                String offer_ForId = "Vaction ID : " + offerForId.getId() + " From: " + offerForId.getAdvertiser();
                if((Boolean)entry.getValue()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Approved");
                    alert.setHeaderText(offerd_id);
                    alert.setHeaderText(offer_ForId);
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Denied");
                    alert.setHeaderText(offerd_id);
                    alert.setHeaderText(offer_ForId);
                    alert.showAndWait();
                }
            }
        }
    }

    public void purchaseVac(ActionEvent actionEvent) throws InterruptedException {
        if(AController.getCurrentUser()==null)
            loginPrompt();
        else{
            super.detectClickAndSet();
            if(AView.purchaseNumber==-1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Vacation picked");
                alert.setHeaderText("Please choose one of the vacations to proceed");

                alert.showAndWait();
            }else {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/PurchaseVacation.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1200, 600);
                    scenes.put("Purchase", scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Purchase Vacation");
                stage.setScene(scene);
                stage.setResizable(false);
                Thread.sleep(250);
                stage.showAndWait();
                if (AView.PaymentApprovement) {
                    super.removeFromTableView(AView.purchaseNumber);
                    super.updateTableView("");
                }
            }
        }
    }

    public void publishVac(ActionEvent actionEvent) throws InterruptedException {
        if(AController.getCurrentUser()==null)
            loginPrompt();
        else{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/PublishVacation.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1200, 600);
                scenes.put("Publish", scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Publish Vacation");
            stage.setScene(scene);
            stage.setResizable(false);
            Thread.sleep(250);
            stage.showAndWait();
            super.updateTableView("");
        }
    }

    /**
     * Method to log you in when not logged in and trying to purchase\publish
     */
    private void loginPrompt(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You aren't logged in");
        alert.setHeaderText("You must log in to Publish a Vacation");
        alert.setContentText("Would you like to log in now?");
        ButtonType LogIn = new ButtonType("Log In");
        ButtonType Cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(LogIn, Cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == LogIn) {
            try {
                this.login();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }

    public void searchVac (ActionEvent actionEvent){
            TextInputDialog dialog = new TextInputDialog("Destination");
            dialog.setTitle("Search for a Destination");
            dialog.setHeaderText("Write the Destination you are looking for");
            dialog.setContentText("Destination:");

            Optional<String> result = dialog.showAndWait();
            try{
                updateTableView(result.get());
            }catch (Exception e){}
        }

    public void Account(ActionEvent actionEvent) throws InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Home.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1200, 600);
            scenes.put("Account", scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Account");
        stage.setScene(scene);
        stage.setResizable(false);
        Thread.sleep(1000);
        stage.show();
    }

    public void logout(ActionEvent actionEvent) throws InterruptedException {
        vacationsController.logout();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logging Out");
        alert.setHeaderText("You will be Logged out automatically");
        alert.showAndWait();
        Thread.sleep(250);
        currentuser.setVisible(false);
        accountButton.setDisable(true);
        LogoutButton.setDisable(true);
        loginButton.setDisable(false);
        watchOffersButton.setDisable(true);
        offerTradeButton.setDisable(true);
    }

    public void offerTrade() {
        super.detectClickAndSet();
        if(AView.purchaseNumber==-1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Vacation picked");
            alert.setHeaderText("Please choose one of the vacations to proceed");
            alert.showAndWait();
            return;
        }
        OfferView.id = AView.purchaseNumber;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/OfferTrade.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1200, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Watch Offers");
        stage.setScene(scene);
        stages.add(stage);
        stage.setResizable(false);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
//        super.updateTableView("");
    }

    public void watchOffers() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/WatchOffers.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1200, 600);
//            scenes.put("Publish", scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Watch Offers");
        stage.setScene(scene);
        stage.setResizable(false);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
    }
}

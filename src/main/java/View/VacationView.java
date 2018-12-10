package View;

import Controller.AController;
import Controller.VacationsController;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
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

    public void setTitle() {
        currentuser.setText("Hello " + AController.getCurrentUser());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.updateTableView();
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
        }
    }

    public void purchaseVac(ActionEvent actionEvent) throws InterruptedException {
        if(AController.getCurrentUser()==null)
            loginPrompt();
        else{
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
            if(AView.PaymentApprovement){
                super.detectClickAndSet();
                super.removeFromTableView(AView.purchaseNumber);
                super.updateTableView();
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
            super.updateTableView();
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
//            System.out.println(result.get());
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
    }
}

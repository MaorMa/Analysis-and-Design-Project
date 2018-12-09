package View;

import Controller.VacationLoggedInController;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class VacationView extends AView implements Initializable {
    public javafx.scene.control.Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void login() throws InterruptedException {
        scenes.put("VacationView", loginButton.getScene());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 900, 600);
            scenes.put("Login", scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();//
    }

    public void purchaseVac(ActionEvent actionEvent) throws InterruptedException {
        loginPrompt();
    }

    public void publishVac() {
        loginPrompt();

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
            System.out.println(result.get());
        }
    }



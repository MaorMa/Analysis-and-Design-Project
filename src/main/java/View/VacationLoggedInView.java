package View;

import Controller.AController;
import Controller.LoginController;
import Controller.VacationLoggedInController;
import Controller.VacationsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VacationLoggedInView extends AView implements Initializable{

    private VacationLoggedInController vacationLoggedInController = new VacationLoggedInController();

    @FXML
    public javafx.scene.control.Label currentuser;

    public void setTitle(){
        currentuser.setText("Hello " + AController.getCurrentUser());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle();
    }

    public void Account(ActionEvent actionEvent) throws InterruptedException {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Home.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 900, 600);
            scenes.put("Account",scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setTitle("Account");
        stage.setScene(scene);
        stage.setResizable(false);
        Thread.sleep(1000);
        stage.show();

    }

    public void purchaseVac(ActionEvent actionEvent) {
    }

    public void publishVac(ActionEvent actionEvent) throws InterruptedException {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/PublishVacation.fxml"));
        Scene scene=null;
        try{
            scene=new Scene(fxmlLoader.load(), 900, 600);
            scenes.put("Publish",scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setTitle("Publish Vacation");
        stage.setScene(scene);
        stage.setResizable(false);
        Thread.sleep(250);
        stage.show();
    }

    public void searchVac(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) throws InterruptedException {
        vacationLoggedInController.logout();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logging Out");
        alert.setHeaderText("You will be Logged out automatically");
        alert.showAndWait();
        Thread.sleep(250);
        mainStage.setScene(scenes.get("VacationView"));
    }
}

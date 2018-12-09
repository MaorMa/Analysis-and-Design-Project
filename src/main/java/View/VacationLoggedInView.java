package View;

import Controller.LoginController;
import Controller.VacationLoggedInController;
import Controller.VacationsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class VacationLoggedInView extends AView{

    private VacationLoggedInController vacationLoggedInController = new VacationLoggedInController();

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

    public void publishVac(ActionEvent actionEvent) {
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

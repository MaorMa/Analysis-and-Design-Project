package View;

import Controller.AController;
import Controller.VacationLoggedInController;
import Model.User;
import Model.Vacation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.sql.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VacationLoggedInView extends AView implements Initializable {

    private VacationLoggedInController vacationLoggedInController = new VacationLoggedInController();

    @FXML
    public javafx.scene.control.Label currentuser;
//    protected javafx.scene.control.TableView vacationLogged = new TableView();

    public void setTitle() {
        currentuser.setText("Hello " + AController.getCurrentUser());
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        setTitle();
//        vacationLogged = super.updateTableView();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTitle();
        super.updateTableView();
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

    public void purchaseVac(ActionEvent actionEvent) throws InterruptedException {
//        initial();
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
        stage.showAndWait();
        if(AView.PaymentApprovement){
            super.detectClickAndSet();
            super.removeFromTableView(AView.purchaseNumber);
            super.updateTableView();
        }
    }

    public void publishVac(ActionEvent actionEvent) throws InterruptedException {
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

    public void searchVac(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("Destination");
        dialog.setTitle("Search for a Destination");
        dialog.setHeaderText("Write the Destination you are looking for");
        dialog.setContentText("Destination:");

        Optional<String> result = dialog.showAndWait();
        System.out.println(result.get());
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

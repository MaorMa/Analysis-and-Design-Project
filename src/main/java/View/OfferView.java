package View;

import Controller.LoginController;
import Controller.OfferController;
import Model.Vacation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

public class OfferView extends AView {

    @FXML
    protected javafx.scene.control.TableView offeredVacationList = new TableView();
    protected javafx.scene.control.Button sendOfferButton;

    private OfferController offerController = new OfferController();
    public static int id;

    public void initialize() {
        ArrayList<Vacation> vacationsList = offerController.getPublishedVacations(id);

        if (vacationsList != null) {
            TableColumn<Vacation,String> column1 = new TableColumn<>("VacationsID");
            column1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getId() + ""));

            TableColumn<Vacation,String> column2 = new TableColumn<>("Advertiser");
            column2.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAdvertiser()));

            TableColumn<Vacation,String> column3 = new TableColumn<>("Airline");
            column3.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAirline()));

            TableColumn<Vacation,String> column4 = new TableColumn<>("Price");
            column4.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPrice() + ""));

            TableColumn<Vacation,String> column5 = new TableColumn<>("ToDestinationDeparture");
            column5.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getToDestinationDeparture() + ""));

            TableColumn<Vacation,String> column6 = new TableColumn<>("Luggage");
            column6.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getLuggage()));

            TableColumn<Vacation,String> column7 = new TableColumn<>("NTickets");
            column7.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNTickets() + ""));

            TableColumn<Vacation,String> column8 = new TableColumn<>("ReturnFlightDeparture");
            column8.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getReturnFlightDeparture() + ""));

            TableColumn<Vacation,String> column9 = new TableColumn<>("Destination");
            column9.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDestination()));

            TableColumn<Vacation,String> column10 = new TableColumn<>("TicketType");
            column10.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTicketType()));

            TableColumn<Vacation,String> column11 = new TableColumn<>("VacationType");
            column11.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getVacationType()));

            TableColumn<Vacation,String> column12 = new TableColumn<>("Accommodation");
            column12.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccommodation()));

            TableColumn<Vacation,String> column13 = new TableColumn<>("AccommodationRank");
            column13.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAccommodationRank() + ""));

            ObservableList<Vacation> items = FXCollections.observableArrayList(FXCollections.observableArrayList(vacationsList));

            if (items.size() >= 0) {
                offeredVacationList.setItems(items.sorted());
                offeredVacationList.getColumns().setAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13);
            }
        }
    }

    public void sendOffer() {
        int choosen = detectVacationChoose();
        if(choosen == -1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Vacation picked");
                alert.setHeaderText("Please choose one of the vacations to proceed");
                alert.showAndWait();
                return;
        }
        offerController.sendOffer(choosen,id);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Success");
        alert.setHeaderText("Offer sent successfully!");
        alert.showAndWait();
        super.closeStage("Watch Offers");

    }

    protected int detectVacationChoose(){
        try {
            TableView.TableViewSelectionModel t = this.offeredVacationList.getSelectionModel();
            int index = t.getFocusedIndex();
            System.out.println(index);
            if(t!=null) {
                Object o = t.getSelectedItem();
                Object i = ((Vacation)o).getId();
                Integer current = (Integer) i;
                if (current != null) {
                    return current;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}

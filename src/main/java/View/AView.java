package View;

import Controller.PublishVacationController;
import Controller.VacationsController;
import Controller.PurchaseVacationController;
import Model.Vacation;
import com.sun.javafx.stage.StageHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AView {
    protected List<Stage> stages;//  = StageHelper.getStages();//get all stages that exist
    protected static HashMap<String, Scene> scenes = new HashMap<>();//
    Stage mainStage = getStage("Vacation4U");
    static int purchaseNumber = -1;
    static boolean PaymentApprovement = false;
    static HashMap<String,String> publishData = new HashMap<>();
    protected static HashMap<Integer, Vacation> vacationsList;
    protected PublishVacationController publishVacationController = new PublishVacationController();
    protected VacationsController vacationsController = new VacationsController();
    protected PurchaseVacationController purchaseVacationController = new PurchaseVacationController();


    @FXML
    protected javafx.scene.control.TableView VacationView = new TableView();

    public TableView updateTableView(String filter) {
        /**
         * test
         */
        vacationsList = vacationsController.getVacations();
        if (vacationsList != null) {
            TableColumn<Map.Entry<Integer, Vacation>, String> column1 = new TableColumn<>("VacationsID");
            column1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getId() + ""));

            TableColumn<Map.Entry<Integer, Vacation>, String> column2 = new TableColumn<>("Advertiser");
            column2.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getAdvertiser()));

            TableColumn<Map.Entry<Integer, Vacation>, String> column3 = new TableColumn<>("Airline");
            column3.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getAirline()));

            TableColumn<Map.Entry<Integer, Vacation>, String> column4 = new TableColumn<>("Price");
            column4.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getPrice() + ""));

            TableColumn<Map.Entry<Integer, Vacation>, String> column5 = new TableColumn<>("ToDestinationDeparture");
            column5.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getToDestinationDeparture() + ""));

            TableColumn<Map.Entry<Integer, Vacation>, String> column6 = new TableColumn<>("Luggage");
            column6.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getLuggage()));

            TableColumn<Map.Entry<Integer, Vacation>, String> column7 = new TableColumn<>("NTickets");
            column7.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getNTickets() + ""));

            TableColumn<Map.Entry<Integer, Vacation>, String> column8 = new TableColumn<>("ReturnFlightDeparture");
            column8.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getReturnFlightDeparture() + ""));

            TableColumn<Map.Entry<Integer, Vacation>, String> column9 = new TableColumn<>("Destination");
            column9.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getDestination()));

            TableColumn<Map.Entry<Integer, Vacation>, String> column10 = new TableColumn<>("TicketType");
            column10.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getTicketType()));

            TableColumn<Map.Entry<Integer, Vacation>, String> column11 = new TableColumn<>("VacationType");
            column11.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getVacationType()));

            TableColumn<Map.Entry<Integer, Vacation>, String> column12 = new TableColumn<>("Accommodation");
            column12.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getAccommodation()));

            TableColumn<Map.Entry<Integer, Vacation>, String> column13 = new TableColumn<>("AccommodationRank");
            column13.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().getAccommodationRank() + ""));

            ObservableList<Map.Entry<Integer, Vacation>> items = FXCollections.observableArrayList(vacationsList.entrySet());
            FilteredList<Map.Entry<Integer, Vacation>> filteredData = new FilteredList<>(items, s -> true);

            if (items.size() >= 0) {
                VacationView.setItems(items.sorted());
                VacationView.getColumns().setAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13);
                //filter
                if(filter == null || filter.equals("") || filter.length() == 0) {
                    filteredData.setPredicate(s -> true);
                    VacationView.setItems(filteredData);
                }
                else {
                    filteredData.setPredicate(s -> s.getValue().getDestination().contains(filter));
                    VacationView.setItems(filteredData);
                }
            }
        }
        return VacationView;
    }

    protected void detectClickAndSet(){
        try {
            Integer current = (Integer) ((Map.Entry) VacationView.getSelectionModel().getSelectedItem()).getKey();
            if(current!=null) {
                setPurchaseNumber(current);
            }

        }catch(Exception e){

        }

    }

    /**
     * Closes a stage
     *
     * @param stageName
     */
    public void closeStage(String stageName) {
        stages = StageHelper.getStages();//get all stages that exist
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i).getTitle().equals(stageName))
                stages.get(i).close();
        }
    }

    public TableView getTableView() {
        return this.VacationView;
    }

    public Stage getStage(String stageName) {
        stages = StageHelper.getStages();
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i).getTitle().equals(stageName))
                return stages.get(i);
        }
        return null;
    }

    public void setPurchaseNumber(Integer toset) {
        this.purchaseNumber = toset;
    }

    public void removeFromTableView(int purchaseNumber) {
        purchaseVacationController.removeVacation(purchaseNumber);
    }

    public HashMap<Integer, Vacation> getVacationList() {
        return this.vacationsList;
    }

    public void setVacationList(HashMap<Integer, Vacation> vacationList) {
        this.vacationsList = vacationList;
    }
}

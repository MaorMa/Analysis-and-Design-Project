package View;

import Controller.RecieveController;
import Model.Trade;
import Model.Vacation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;

public class RecieveView extends AView {

    RecieveController recieveController = new RecieveController();

    @FXML
    protected javafx.scene.control.TableView OffersTableView = new TableView();


    public void initialize() {
        updateOffersTable();
    }

    public void acceptTrade() {
        respondForTrade(true);
    }

    public void declineTrade() {
        respondForTrade(false);
    }

    public void respondForTrade(boolean ans) {
        TableView.TableViewSelectionModel t = this.OffersTableView.getSelectionModel();
        int index = t.getFocusedIndex();

        ArrayList<Trade> tradesList = recieveController.receiveOffers();
        ArrayList<Vacation> toShowTradeList=new ArrayList<>();
        for(Trade trade : tradesList) {
            toShowTradeList.add(trade.offerForId);
            toShowTradeList.add(trade.offerId);
            toShowTradeList.add(null);
        }

        if((index+1)%3!=0) {
            int offered=toShowTradeList.get((index/3)*3+1).getId(),
            offeredFor=toShowTradeList.get((index/3)*3).getId();
            recieveController.sendResponse(offeredFor, offered, ans);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Vacation picked");
            alert.setHeaderText("Please choose one of the vacations to proceed");
            alert.showAndWait();
            return;
        }
        updateOffersTable();
    }

    public void updateOffersTable(){
        ArrayList<Trade> tradesList = recieveController.receiveOffers();
        ArrayList<Vacation> toShowTradeList=new ArrayList<>();
        int i=1;
        for(Trade t : tradesList) {
            if(i%2==0)
                toShowTradeList.add(null);

            toShowTradeList.add(t.offerForId);
            toShowTradeList.add(t.offerId);

            i++;
        }


        if (tradesList != null) {
            TableColumn<Vacation,String> column1 = new TableColumn<>("VacationsID");
            column1.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getId()+"");
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column2 = new TableColumn<>("Advertiser");
            column2.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getAdvertiser());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column3 = new TableColumn<>("Airline");
            column3.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getAirline());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column4 = new TableColumn<>("Price");
            column4.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getPrice()+"");
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column5 = new TableColumn<>("ToDestinationDeparture");
            column5.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getToDestinationDeparture());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column6 = new TableColumn<>("Luggage");
            column6.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getLuggage());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column7 = new TableColumn<>("NTickets");
            column7.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getNTickets()+"");
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column8 = new TableColumn<>("ReturnFlightDeparture");
            column8.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getReturnFlightDeparture());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column9 = new TableColumn<>("Destination");
            column9.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getDestination());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column10 = new TableColumn<>("TicketType");
            column10.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getTicketType());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column11 = new TableColumn<>("VacationType");
            column11.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getVacationType());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column12 = new TableColumn<>("Accommodation");
            column12.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getAccommodation());
                else return new SimpleStringProperty(" ");
            });

            TableColumn<Vacation,String> column13 = new TableColumn<>("AccommodationRank");
            column13.setCellValueFactory(p ->{
                if (p.getValue() != null)
                    return new SimpleStringProperty(p.getValue().getAccommodationRank()+"");
                else return new SimpleStringProperty(" ");
            });

            ObservableList<Vacation> items = FXCollections.observableArrayList(FXCollections.observableArrayList(toShowTradeList));

            if (items.size() >= 0) {
                OffersTableView.setItems(items);
                OffersTableView.getColumns().setAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13);
            }
        }
    }
}

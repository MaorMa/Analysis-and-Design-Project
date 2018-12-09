package View;

import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PublishVacationView extends AView implements Initializable {

        @FXML
        public javafx.scene.control.TextField destination;
        public javafx.scene.control.DatePicker dfdate;
        public javafx.scene.control.DatePicker dbdate;
        public javafx.scene.control.ChoiceBox numTickets;
        public javafx.scene.control.ChoiceBox ticketType;
        public javafx.scene.control.ChoiceBox vacType;
        public javafx.scene.control.TextField airline;
        public javafx.scene.control.TextField accName;
        public javafx.scene.control.ChoiceBox accRank;
        public javafx.scene.control.TextField luggWeight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            numTickets.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
            numTickets.setValue(1);
//         numTickets = new ChoiceBox(FXCollections.observableArrayList(
//                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
//        );
        ticketType.getItems().addAll("1-12","13-60","60-100");
        ticketType.setValue("1-12");
//        ticketType = new ChoiceBox(FXCollections.observableArrayList(
//                "1-12","13-60","60-100")
//        );
        vacType.getItems().addAll("Urban Vacation","Vacation in Nature", "Beach Vacation",
                "Family Vacation","Cheap Vacation", "Ski Vacation", "Romantic  Vacation");
        vacType.setValue("Urban Vacation");
//        vacType = new ChoiceBox(FXCollections.observableArrayList(
//                "Urban Vacation","Vacation in Nature", "Beach Vacation",
//                "Family Vacation","Cheap Vacation", "Ski Vacation", "Romantic  Vacation" ));
        accRank.getItems().addAll(1,2,3,4,5);
        accRank.setValue(1);
//        accRank = new ChoiceBox(FXCollections.observableArrayList("1","2","3","4","5"));
    }

    public void publishVac () {
        String destinationS = destination.getText();
        String accNameS = accName.getText();
        String luggWeightS = luggWeight.getText();
        String airlineS = airline.getText();
        String dfdateS, dbdateS;
        if(dfdate.getValue() != null)//check if date is null
        {
            dfdateS = dfdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//problem when empty!
        }
        else {
            dfdateS = "";//if date is null - set to empty'
        }
        if(dbdate.getValue() != null)//check if date is null
        {
            dbdateS = dbdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//problem when empty!
        }
        else {
            dbdateS = "";//if date is null - set to empty'
        }
        int numTicketsS = (int) numTickets.getValue();
        String ticketTypeS = (String) ticketType.getValue();
        if(ticketTypeS==null)
            ticketTypeS="";
        String vacTypeS = (String) vacType.getValue();
        if(vacTypeS==null)
            vacTypeS="";
        int accRankS = (int) accRank.getValue();
        if(destinationS.isEmpty() || accNameS.isEmpty() || luggWeightS.isEmpty() || airlineS.isEmpty() || dfdateS.isEmpty() ||
                dbdateS.isEmpty() || ticketTypeS.isEmpty() || vacTypeS.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Error while filling in the Vacation Information");
            alert.setContentText("One of the fields was left blank");

            alert.showAndWait();
        }
        else{
            //Vacation vacation = new Vacation();
        }
    }
}

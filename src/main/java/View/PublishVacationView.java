package View;

import Controller.AController;
import Controller.PublishVacationController;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ResourceBundle;

public class PublishVacationView extends AView implements Initializable {
    private PublishVacationController publishVacationController = new PublishVacationController();
    public javafx.scene.control.Button publishButton;

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
    public javafx.scene.control.TextField price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numTickets.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        numTickets.setValue(1);

        ticketType.getItems().addAll("Adult","Child","Baby");
        ticketType.setValue("Adult");

        vacType.getItems().addAll("Urban","Exotic", "Other");
        vacType.setValue("Urban");

        accRank.getItems().addAll(0,1,2,3,4,5);
        accRank.setValue(0);
    }

    public void publishVac() throws InterruptedException {
        String destinationS = destination.getText();
        String accNameS = accName.getText();
        String luggWeightS = luggWeight.getText();
        String airlineS = airline.getText();
        String dfdateS, dbdateS;
        double priced=0;
        boolean isDouble;
        try {
            priced = Double.parseDouble(price.getText());
            isDouble=true;
        }catch(Exception e){
            isDouble=false;
        }
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
        if(!isDouble || destinationS.isEmpty() || luggWeightS.isEmpty() || airlineS.isEmpty() || dfdateS.isEmpty() ||
                dbdateS.isEmpty() || ticketTypeS.isEmpty() || vacTypeS.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Error while filling in the Vacation Information");
            alert.setContentText("One of the fields was left blank");

            alert.showAndWait();
        }
        else{
            Vacation vacation = new Vacation(numTicketsS,publishVacationController.getUsername(),airlineS,destinationS,
                    ticketTypeS,vacTypeS,priced,stringToDate(dfdateS));
            if(accNameS.isEmpty())//add accomidation name
                vacation.setAccommodation(accNameS);
            if(accRankS!=0)//add accomidation rank
                vacation.setAccommodationRank(accRankS);
            vacation.setId(publishVacationController.addVacation(vacation));
            if(vacation.getId()!=-1){
                Thread.sleep(500);
                Stage stage = (Stage) publishButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     *
     * @param stringDate - string date from the date controller
     * @return -
     */
    private Date stringToDate(String stringDate){
        String[] splitDate = stringDate.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[2]);
        return new Date(year,month,day);
    }
}
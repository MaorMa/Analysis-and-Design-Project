package Model;

import java.sql.Date;

/**
 * Not created by Maor. He's just an assistant.
 */
public class Vacation {
    int id, accommodationRank, NTickets;
    User advertiser;
    String airline, luggage, destination, ticketType, vacationType, accommodation;
    double price;
    Date toDestinationDeparture, returnFlightDeparture;

    public Vacation(int id, int accommodationRank, int NTickets, User advertiser, String airline, String luggage, String destination, String ticketType, String vacationType, String accommodation, double price, Date toDestinationDeparture, Date returnFlightDeparture) {
        this.id = id;
        this.accommodationRank = accommodationRank;
        this.NTickets = NTickets;
        this.advertiser = advertiser;
        this.airline = airline;
        this.luggage = luggage;
        this.destination = destination;
        this.ticketType = ticketType;
        this.vacationType = vacationType;
        this.accommodation = accommodation;
        this.price = price;
        this.toDestinationDeparture = toDestinationDeparture;
        this.returnFlightDeparture = returnFlightDeparture;
    }

    public int getId() {
        return id;
    }

    public int getAccommodationRank() {
        return accommodationRank;
    }

    public void setAccommodationRank(int accommodationRank) {
        this.accommodationRank = accommodationRank;
    }

    public int getNTickets() {
        return NTickets;
    }

    public void setNTickets(int NTickets) {
        this.NTickets = NTickets;
    }

    public User getAdvertiser() {
        return advertiser;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getVacationType() {
        return vacationType;
    }

    public void setVacationType(String vacationType) {
        this.vacationType = vacationType;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getToDestinationDeparture() {
        return toDestinationDeparture;
    }

    public void setToDestinationDeparture(Date toDestinationDeparture) {
        this.toDestinationDeparture = toDestinationDeparture;
    }

    public Date getReturnFlightDeparture() {
        return returnFlightDeparture;
    }

    public void setReturnFlightDeparture(Date returnFlightDeparture) {
        this.returnFlightDeparture = returnFlightDeparture;
    }
}
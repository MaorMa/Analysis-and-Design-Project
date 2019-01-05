package Model;

/**
 *  Database connection class
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

//todo check db file
public class DBconnection {
    private Connection conn = null;
    private int vacationID=0, paymentID =0;

    public DBconnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:res/Users.db");
            createTables();
            updatePaymentID();
            updateVacationID();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Connection established successfully");
    }

    private void updateVacationID(){
        String selectQ = "SELECT VacationID FROM Vacations ORDER BY VacationID DESC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQ)){
            if(rs.next())
                vacationID=rs.getInt("VacationID");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void updatePaymentID(){
        String selectQ = "SELECT PaymentID FROM Payments ORDER BY PaymentID DESC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQ)){
            if(rs.next())
                paymentID=rs.getInt("PaymentID");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void createTables(){
        String createUseres = "CREATE TABLE IF NOT EXISTS `Users` (\n" +
                "\t`UserName`\tTEXT NOT NULL,\n" +
                "\t`Password`\ttext NOT NULL,\n" +
                "\t`BDate`\tNUMERIC NOT NULL,\n" +
                "\t`FName`\tTEXT NOT NULL,\n" +
                "\t`LName`\tTEXT NOT NULL,\n" +
                "\t`City`\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(`UserName`)\n" +
                ");";
        String createVacations="CREATE TABLE IF NOT EXISTS `Vacations` (\n" +
                "\t`VacationID`\tNUMERIC NOT NULL,\n" +
                "\t`Advertiser`\tTEXT NOT NULL,\n" +
                "\t`Airline`\tTEXT NOT NULL,\n" +
                "\t`Price`\tNUMERIC NOT NULL,\n" +
                "\t`ToDestinationDeparture`\tTEXT NOT NULL,\n" +
                "\t`Luggage`\tTEXT,\n" +
                "\t`NTickets`\tNUMERIC NOT NULL,\n" +
                "\t`ReturnFlightDeparture`\tTEXT,\n" +
                "\t`Destination`\tTEXT NOT NULL,\n" +
                "\t`TicketType`\tNOT NULL CHECK (TicketType IN ('ADULT', 'CHILD', 'BABY')),\n" +
                "\t`VacationType`\tNOT NULL CHECK (VacationType IN ('URBAN', 'EXOTIC', 'OTHER')),\n" +
                "\t`Accommodation`\tTEXT,\n" +
                "\t`AccommodationRank`\tNUMERIC,\n" +
                "\tPRIMARY KEY(`VacationID`), " +
                "FOREIGN KEY(`Advertiser`) REFERENCES Users(`UserName`)\n" +
                ");";
        String createPayments="CREATE TABLE IF NOT EXISTS `Payments` (\n" +
                "\t`PaymentID`\tNUMERIC NOT NULL,\n" +
                "\t`VacationID`\tNUMERIC NOT NULL,\n" +
                "\t`Buyer`\tTEXT NOT NULL,\n" +
                "\t`Seller`\tTEXT NOT NULL,\n" +
                "\t`Amount`\tNUMERIC NOT NULL,\n" +
                "\t`Method`\tNOT NULL CHECK (Method IN ('PAYPAL', 'VISA')),\n" +
                "\tPRIMARY KEY(`PaymentID`),\n " +
                "FOREIGN KEY(`VacationID`) REFERENCES Vacations(`VacationID`)\n" +
                "FOREIGN KEY(`Buyer`) REFERENCES Users(`UserName`)\n" +
                "FOREIGN KEY(`Seller`) REFERENCES Users(`UserName`)\n" +
                ");";
        String createTrades="CREATE TABLE IF NOT EXISTS `Trades` (\n" +
                "\t`OfferedVacationID`\tINTEGER NOT NULL,\n" +
                "\t`OfferedForVacationID`\tINTEGER NOT NULL,\n" +
                "\t`Approved`\tINTEGER NOT NULL CHECK(Approved IN ( 'Y' , 'N' )),\n" +
                "\tFOREIGN KEY(`OfferedForVacationID`) REFERENCES `Vacations`(`VacationID`),\n" +
                "\tPRIMARY KEY(`OfferedVacationID`,`OfferedForVacationID`),\n" +
                "\tFOREIGN KEY(`OfferedVacationID`) REFERENCES `Vacations`(`VacationID`)\n" +
                ");";
        try{
            PreparedStatement pstmt = conn.prepareStatement(createUseres);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(createVacations);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(createPayments);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(createTrades);
            pstmt.executeUpdate();
            //System.out.println("UsersCreated Complete");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean insertUser(User user) {
        String insertQ = "INSERT INTO Users(UserName,Password,FName,LName,BDate,City) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getBdate());
            pstmt.setString(4, user.getFirst_name());
            pstmt.setString(5, user.getLast_name());
            pstmt.setString(6, user.getCity());
            pstmt.executeUpdate();
            //System.out.println("Insert Complete");
            return true;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return false;
        }
    }

    public void updateUser(String oldUserName, User newUser) {
        String insertQ = "UPDATE Users\n " +
                "SET UserName=?,Password=?,BDate=?,FName=?,LName=?,City=?\n " +
                "WHERE UserName='"+oldUserName+"'";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getBdate());
            pstmt.setString(4, newUser.getFirst_name());
            pstmt.setString(5, newUser.getLast_name());
            pstmt.setString(6, newUser.getCity());
            pstmt.executeUpdate();
            //System.out.println("Update Complete");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeUser(String username) {
        String removeQ = "DELETE FROM Users\n" +
                "WHERE UserName='" + username + "';";

        try (PreparedStatement pstmt = conn.prepareStatement(removeQ)) {
            pstmt.execute();
            //System.out.println("Delete Complete");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User readUser(String userName){
        String selectQ = "SELECT * FROM Users WHERE UserName="+"\""+userName+"\"";
        User currentUser = null;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQ)){
            currentUser = new User(rs.getString("UserName"),rs.getString("Password"),
                    rs.getString("BDate"),rs.getString("FName"),rs.getString("LName"),
                    rs.getString("City"));
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return currentUser;
    }

    public boolean validateUser(String userName, String password){
        String selectQ = "SELECT Username,Password FROM Users WHERE UserName="+"\""+userName+"\"" +"AND Password="+"\""+password+"\"";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQ)){
            if(!rs.next()) {
                stmt.close();
                return false;
            }
            else{
                stmt.close();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int insertPayment(Payment pay){
        String insertQ = "INSERT INTO Payments(PaymentID, VacationID ,Buyer, Seller, Amount, Method) VALUES(?,?,?,?,?,?)";
        int id=getPaymentID();
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
//            System.out.println(id+", "+pay.getVacation().getId()+", "+pay.getBuyer()+", "+pay.getVacation().getAdvertiser()+", "+pay.getVacation().getPrice()+", "+pay.getMethod());
            pstmt.setInt(1,id);
            pstmt.setInt(2, pay.getVacation().getId());
            pstmt.setString(3, pay.getBuyer());
            pstmt.setString(4, pay.getVacation().getAdvertiser());
            pstmt.setDouble(5, pay.getVacation().getPrice());
            pstmt.setString(6, pay.getMethod().toUpperCase());
            pstmt.executeUpdate();
            //System.out.println("Insert Complete");
            return id;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public int insertVacation(Vacation vacation){
        String insertQ = "INSERT INTO Vacations(VacationID, Advertiser, Airline, Price, ToDestinationDeparture, Luggage, NTickets, ReturnFlightDeparture, Destination, TicketType, VacationType, Accommodation, AccommodationRank) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int id=getVacationID();
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, vacation.getAdvertiser());
            pstmt.setString(3, vacation.getAirline());
            pstmt.setDouble(4, vacation.getPrice());
            pstmt.setString(5, vacation.getToDestinationDeparture());
            pstmt.setString(6, vacation.getLuggage());
            pstmt.setInt(7, vacation.getNTickets());
            pstmt.setString(8, vacation.getReturnFlightDeparture());
            pstmt.setString(9, vacation.getDestination().toUpperCase());
            pstmt.setString(10, vacation.getTicketType().toUpperCase());
            pstmt.setString(11, vacation.getVacationType().toUpperCase());
            pstmt.setString(12, vacation.getAccommodation());
            pstmt.setInt(13, vacation.getAccommodationRank());
            pstmt.executeUpdate();
            //System.out.println("Insert Complete");
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
            return -1;
        }
    }

    public Vacation readVacation(int id){
        String readQ = "SELECT * FROM Vacations WHERE VacationID=\'"+id+"\'";
        try (PreparedStatement pstmt = conn.prepareStatement(readQ)) {
            ResultSet resultSet=pstmt.executeQuery();
            String advertiser=resultSet.getString("Advertiser");
            String airline=resultSet.getString("Airline");
            double price=resultSet.getDouble("Price");
            String toDestination=resultSet.getString("ToDestinationDeparture");
            String luggage=resultSet.getString("Luggage");
            int NTickets=resultSet.getInt("NTickets");
            String returnFlight=resultSet.getString("ReturnFlightDeparture");
            String destination=resultSet.getString("Destination");
            String ticketType=resultSet.getString("TicketType");
            String vacationType= resultSet.getString("VacationType");
            String accommodation=resultSet.getString("Accommodation");
            int accommodationRank=resultSet.getInt("AccommodationRank");
            Vacation vacation=new Vacation(NTickets, advertiser, airline, destination, ticketType, vacationType, price, toDestination);
            vacation.setId(resultSet.getInt("VacationID"));
            vacation.setLuggage(luggage);
            vacation.setToDestinationDeparture(resultSet.getString("ToDestinationDeparture"));
            vacation.setAccommodation(accommodation);
            vacation.setAccommodationRank(accommodationRank);
            vacation.setReturnFlightDeparture(returnFlight);
            //System.out.println("Insert Complete");
            return vacation;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public HashMap<Integer, Vacation> readVacations(){
        String readQ = "SELECT * FROM Vacations V WHERE NOT EXISTS (SELECT * FROM Payments P WHERE P.VacationID=V.VacationID)";
        try (PreparedStatement pstmt = conn.prepareStatement(readQ)) {
            ResultSet resultSet=pstmt.executeQuery();
            HashMap<Integer, Vacation> ans=new HashMap<>();
            while(resultSet.next()) {
                int id=resultSet.getInt("VacationID");
                String advertiser = resultSet.getString("Advertiser");
                String airline = resultSet.getString("Airline");
                double price = resultSet.getDouble("Price");
                String toDestination = resultSet.getString("ToDestinationDeparture");
                String luggage = resultSet.getString("Luggage");
                int NTickets = resultSet.getInt("NTickets");
                String returnFlight = resultSet.getString("ReturnFlightDeparture");
                String destination = resultSet.getString("Destination");
                String ticketType = resultSet.getString("TicketType");
                String vacationType = resultSet.getString("VacationType");
                String accommodation = resultSet.getString("Accommodation");
                int accommodationRank = resultSet.getInt("AccommodationRank");
                Vacation vacation = new Vacation(NTickets, advertiser, airline, destination,
                        ticketType, vacationType, price, toDestination);
                vacation.setId(id);
                vacation.setLuggage(luggage);
                vacation.setReturnFlightDeparture(returnFlight);
                vacation.setAccommodation(accommodation);
                vacation.setAccommodationRank(accommodationRank);
                ans.put(id, vacation);
            }
            //System.out.println("Insert Complete");
            return ans;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void removeVacation (int id){
        String removeQ = "DELETE FROM Vacations\n" +
                "WHERE VacationID=" + id + ";";
        try (PreparedStatement pstmt = conn.prepareStatement(removeQ)) {
            pstmt.execute();
            //System.out.println("Delete vacation Complete");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateVacationID();
    }

    private int getPaymentID(){
        return ++paymentID;
    }

    private int getVacationID(){
        return ++vacationID;
    }

    public static void main(String[] args) {
        try {
            DBconnection db = new DBconnection();
            db.createTables();
            for(Trade t : db.receiveOffers("aviv"))
                System.out.println(t.vacationA.id+"  "+t.getVacationB.id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param userName
     * @return
     */
    public ArrayList<Trade> receiveOffers(String userName){
        String readQ = "select OfferedForVacationID, OfferedVacationID\n" +
                "from Trades as T join \n" +
                "(select *\n" +
                    "\tfrom Vacations\n" +
                    "\twhere Vacations.Advertiser=?) as V\n" +
                "on T.OfferedForVacationID=V.VacationID";
        try {
            PreparedStatement pstmt = conn.prepareStatement(readQ);
            pstmt.setString(1, userName);
            ResultSet resultSet=pstmt.executeQuery();
            ArrayList<Trade> ans=new ArrayList<>();
            while(resultSet.next()) {
                int offeredID=resultSet.getInt("OfferedVacationID");
                int offeredForID=resultSet.getInt("OfferedForVacationID");
                Vacation offeredVacation=readVacation(offeredID);
                Vacation offeredForVacation=readVacation(offeredForID);
                Trade t=new Trade();
                t.vacationA=offeredVacation;
                t.getVacationB=offeredForVacation;
                ans.add(t);
            }
            return ans;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all the vacations that a user has published and have not been sold.
     * @param userName The user who's connected.
     * @return A list of vacations.
     */
    public ArrayList<Vacation> getPublishedVacations(String userName){
        String readQ = "SELECT * " +
                "FROM Vacations V "+
                "WHERE Advertiser=\'"+userName+"\'";
        try (PreparedStatement pstmt = conn.prepareStatement(readQ)) {
            ResultSet resultSet=pstmt.executeQuery();
            ArrayList<Vacation> ans=new ArrayList<>();
            while(resultSet.next()) {
                int id=resultSet.getInt("VacationID");
                String advertiser = resultSet.getString("Advertiser");
                String airline = resultSet.getString("Airline");
                double price = resultSet.getDouble("Price");
                String toDestination = resultSet.getString("ToDestinationDeparture");
                String luggage = resultSet.getString("Luggage");
                int NTickets = resultSet.getInt("NTickets");
                String returnFlight = resultSet.getString("ReturnFlightDeparture");
                String destination = resultSet.getString("Destination");
                String ticketType = resultSet.getString("TicketType");
                String vacationType = resultSet.getString("VacationType");
                String accommodation = resultSet.getString("Accommodation");
                int accommodationRank = resultSet.getInt("AccommodationRank");
                Vacation vacation = new Vacation(NTickets, advertiser, airline, destination,
                        ticketType, vacationType, price, toDestination);
                vacation.setId(id);
                vacation.setLuggage(luggage);
                vacation.setReturnFlightDeparture(returnFlight);
                vacation.setAccommodation(accommodation);
                vacation.setAccommodationRank(accommodationRank);
                ans.add(vacation);
            }
            return ans;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Insert an offer for a trade.
     * @param trade
     */
    public void sendOffer(Trade trade){
        String insertQ = "INSERT INTO Trades VALUES(?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setInt(1, trade.vacationA.id);
            pstmt.setInt(2, trade.getVacationB.id);
            pstmt.setString(3, "'N'");
            pstmt.executeUpdate();
            //System.out.println("Insert Complete");
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }

    /**
     * Update seller response to a trade offer.
     * @param flag
     * @param trade
     */
    public void sendResponse(boolean flag, Trade trade){
        String insertQ = "UPDATE Trades\n" +
                "SET Approved=?\n" +
                "WHERE OfferedVacationID=? AND OfferedForVacationID=?";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setInt(1, trade.vacationA.id);
            pstmt.setInt(2, trade.getVacationB.id);
            if(flag)
                pstmt.setString(3, "'Y'");
            else pstmt.setString(3, "'N'");
            pstmt.executeUpdate();
            //System.out.println("Insert Complete");
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }
}
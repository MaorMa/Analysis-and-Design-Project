package Model;

/**
 *  Database connection class
 */

import java.sql.*;

//todo check db file
public class DBconnection {
    private Connection conn = null;
    private int vacationID=0, paymentID =0;

    public DBconnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:res/Users.db");
            createTables();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Connection established successfully");
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
                "\t`ToDestinationDeparture`\tDATE NOT NULL,\n" +
                "\t`Luggage`\tTEXT,\n" +
                "\t`NTickets`\tNUMERIC NOT NULL,\n" +
                "\t`ReturnFlightDeparture`\tDATE,\n" +
                "\t`Destination`\tTEXT NOT NULL,\n" +
                "\t`TicketsType`\tNOT NULL CHECK (TicketsType IN ('Adult', 'Chile', 'Baby')),\n" +
                "\t`VacationType`\tNOT NULL CHECK (VacationType IN ('Urban', 'Exotic', 'Other')),\n" +
                "\t`Accommodation`\tTEXT,\n" +
                "\t`AccommodationRank`\tNUMERIC,\n" +
                "\tPRIMARY KEY(`VacationID`), " +
                "FOREIGN KEY(`Advertiser`) REFERENCES Users(`UserName`)\n" +
                ");";
        String createPayments="CREATE TABLE IF NOT EXISTS `Payments` (\n" +
                "\t`VacationID`\tNUMERIC NOT NULL,\n" +
                "\t`Buyer`\tTEXT NOT NULL,\n" +
                "\t`Seller`\tTEXT NOT NULL,\n" +
                "\t`Amount`\tNUMERIC NOT NULL,\n" +
                "\t`Method`\tNOT NULL CHECK (Method IN ('Paypal', 'Visa')),\n" +
                "\tPRIMARY KEY(`VacationID`), " +
                "FOREIGN KEY(`VacationID`) REFERENCES Users(`VacationID`)\n" +
                "FOREIGN KEY(`Buyer`) REFERENCES Users(`UserName`)\n" +
                "FOREIGN KEY(`Seller`) REFERENCES Users(`UserName`)\n" +
                ");";
        try{
            PreparedStatement pstmt = conn.prepareStatement(createUseres);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(createPayments);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(createVacations);
            pstmt.executeUpdate();
            //System.out.println("UsersCreated Complete");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
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
        }
    }

    public void removeUser(String username) {
        String removeQ = "DELETE FROM Users\n" +
                "WHERE UserName='" + username + "';";

        try (PreparedStatement pstmt = conn.prepareStatement(removeQ)) {
            pstmt.execute();
            //System.out.println("Delete Complete");
        } catch (SQLException e) {
            //e.printStackTrace();
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
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int insertPayment(User buyer, Vacation vacation, String method){
        String insertQ = "INSERT INTO Payment(PaymentID, VacationID ,Buyer, Seller, Amount, Method) VALUES(?,?,?,?,?,?)";
        int id=getPaymentID();
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, vacation.getId());
            pstmt.setString(3, buyer.getUsername());
            pstmt.setString(4, vacation.getAdvertiser().getUsername());
            pstmt.setDouble(5, vacation.getPrice());
            pstmt.setString(6, method);
            pstmt.executeUpdate();
            //System.out.println("Insert Complete");
            return id;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return -1;
        }
    }

    public int insertVacation(Vacation vacation){
        String insertQ = "INSERT INTO Vacation(VacationID, Advertiser, Airline, Price, ToDestinationDeparture, Luggage, NTickets, ReturnFlightDeparture, Destination, TicketType, VacationType, Accommodation, AccommodationRank) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int id=getVacationID();
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, vacation.getAdvertiser().getUsername());
            pstmt.setString(3, vacation.getAirline());
            pstmt.setDouble(4, vacation.getPrice());
            pstmt.setDate(5, vacation.getToDestinationDeparture());
            pstmt.setString(6, vacation.getLuggage());
            pstmt.setInt(7, vacation.getNTickets());
            pstmt.setDate(8, vacation.getReturnFlightDeparture());
            pstmt.setString(9, vacation.getDestination());
            pstmt.setString(10, vacation.getTicketType());
            pstmt.setString(11, vacation.getVacationType());
            pstmt.setString(12, vacation.getAccommodation());
            pstmt.setInt(13, vacation.getAccommodationRank());
            pstmt.executeUpdate();
            //System.out.println("Insert Complete");
            return id;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return -1;
        }
    }

    public Vacation readVacation(int id){
        String readQ = "SELECT * FROM Vacations WHERE VacationID=\'"+id+"\'";
        try (PreparedStatement pstmt = conn.prepareStatement(readQ)) {
            ResultSet resultSet=pstmt.executeQuery();
            User advertiser=readUser(resultSet.getString("Advertiser"));
            String airline=resultSet.getString("Airline");
            double price=resultSet.getDouble("Price");

            //Vacation vacation=new Vacation(id,);
            //System.out.println("Insert Complete");
            return null;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    private int getPaymentID(){
        return ++paymentID;
    }

    private int getVacationID(){
        return ++vacationID;
    }
}
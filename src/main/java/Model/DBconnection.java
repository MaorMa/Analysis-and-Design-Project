package Model;

/**
 *  Database connection class
 */

import Model.User;

import java.sql.*;

//todo check db file
public class DBconnection {

    private Connection conn = null;

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
        String createQuary = "CREATE TABLE IF NOT EXISTS `Users` (\n" +
                "\t`UserName`\tTEXT NOT NULL,\n" +
                "\t`Password`\ttext NOT NULL,\n" +
                "\t`BDate`\tNUMERIC NOT NULL,\n" +
                "\t`FName`\tTEXT NOT NULL,\n" +
                "\t`LName`\tTEXT NOT NULL,\n" +
                "\t`City`\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(`UserName`)\n" +
                ");";
        try (PreparedStatement pstmt = conn.prepareStatement(createQuary)) {
            pstmt.executeUpdate();
            //System.out.println("UsersCreated Complete");
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    private boolean insertUser(User user) {
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

    private void updateUser(String oldUserName, User newUser) {
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

    private void removeUser(String username) {
        String removeQ = "DELETE FROM Users\n" +
                "WHERE UserName='" + username + "';";

        try (PreparedStatement pstmt = conn.prepareStatement(removeQ)) {
            pstmt.execute();
                //System.out.println("Delete Complete");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    private User readUser(String userName){
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

    private boolean validateUser(String userName,String password){
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

}
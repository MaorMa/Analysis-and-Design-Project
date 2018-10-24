package Controller;

/**
 * Created by Maor on 10/17/2018.
 */
import Model.User;

import java.sql.*;

public class DBconnection {
    /**
     *
     * @return
     */
    Connection conn = null;

    public DBconnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:res/Users.db");
            createTables();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Connection established successfully");
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
            System.out.println("UsersCreated Complete");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insertUser(User user) {
        String insertQ = "INSERT INTO Users(UserName,Password,BDate,FName,LName,City) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getBdate());
            pstmt.setString(4, user.getFirst_name());
            pstmt.setString(5, user.getLast_name());
            pstmt.setString(6, user.getCity());
            pstmt.executeUpdate();
                System.out.println("Insert Complete");
                return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void updateUser(User oldUser, User newUser) {
        String insertQ = "UPDATE Users\n " +
                "SET UserName=?,Password=?,BDate=?,FName=?,LName=?,City=?\n " +
                "WHERE UserName='"+oldUser.getUsername()+"'";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getBdate());
            pstmt.setString(4, newUser.getFirst_name());
            pstmt.setString(5, newUser.getLast_name());
            pstmt.setString(6, newUser.getCity());
            pstmt.executeUpdate();
            System.out.println("Update Complete");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUser(User user) {
        String removeQ = "DELETE FROM Users\n" +
                "WHERE UserName='" + user.getUsername() + "';";

        try (PreparedStatement pstmt = conn.prepareStatement(removeQ)) {
            pstmt.execute();
                System.out.println("Delete Complete");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readUser(String userName){
        String selectQ = "SELECT * FROM Users WHERE UserName="+"\""+userName+"\"";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(selectQ)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("id") +  "\t"+
                        rs.getString("BDate")+"\t"+
                        rs.getString("FName")+"\t"+
                        rs.getString("LName")+"\t"+
                        rs.getString("City"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DBconnection db=new DBconnection();
        db.insertUser(new User("saarm", "123", "04/11/90", "a","c", "a"));
        db.updateUser(new User("saarm", null,null,null,null,null),
                new User("aviv", "123", "04/11/90", "a","c", "a"));
    }
}
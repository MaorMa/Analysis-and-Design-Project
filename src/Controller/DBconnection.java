package Controller;

/**
 * Created by Maor on 10/17/2018.
 */
import java.sql.*;

public class DBconnection {
    private Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Connection established successfully");
        return c;

    }

    public void insert(String name, double capacity) {
        String createTable="CREATE TABLE users(UserName, Password)";
        String sql = "INSERT INTO users(UserName, Password) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DBconnection d=new DBconnection();
        d.insert("Raw Materials", 3000);

    }
}
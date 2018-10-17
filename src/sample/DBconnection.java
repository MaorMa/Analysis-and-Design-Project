package sample;

/**
 * Created by Maor on 10/17/2018.
 */
import java.sql.*;

public class DBconnection {
    public static void main( String args[] ) {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Connection established successfully");
    }
}
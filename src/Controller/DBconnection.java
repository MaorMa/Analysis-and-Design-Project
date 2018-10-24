package Controller;

/**
 * Created by Maor on 10/17/2018.
 */
import java.sql.*;

public class DBconnection {
    /**
     *
     * @return
     */
    Connection conn = null;

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:res/users.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Connection established successfully");
    }

    public void createNewTable(String tableName, String cols) {
        // SQL statement for creating a new table
        String newTable = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n" + cols + ");";

        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(newTable);
                System.out.println("Table Created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String name, double capacity) {
        String insertQ = "INSERT INTO users(name,capacity) VALUES(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQ)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
                System.out.println("Insert Complete");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeRow(String tableName, String key) {
        String removeQ = "DELETE FROM " + tableName + "\n" +
                "WHERE name='" + key + "';";

        try (PreparedStatement pstmt = conn.prepareStatement(removeQ)) {
            pstmt.execute();
                System.out.println("Delete Complete");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectAll(){
        String selectQ = "SELECT id, name, capacity FROM users";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(selectQ)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DBconnection db=new DBconnection();
        db.connect(); //create global connection
        //db.createNewTable("users", "id integer PRIMARY KEY, name text NOT NULL, capacity real");
        //db.insert("Raw", 3000);
        //db.removeRow("users","Raw");
        //db.selectAll();
    }
}
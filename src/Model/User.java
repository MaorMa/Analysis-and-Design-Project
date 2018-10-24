package Model;

import java.sql.Date;

/**
 * Created by Maor on 10/24/2018.
 */
public class User {
    private String username;
    private String password;
    private String bdate;
    private String fName;
    private String lName;
    private String city;

    /**
     C'tor
     */
    public User(String username, String password, String bdate, String first_name, String last_name, String city) {
        this.username = username;
        this.password = password;
        this.bdate = bdate;
        this.fName = first_name;
        this.lName = last_name;
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getFirst_name() {
        return fName;
    }

    public void setFirst_name(String first_name) {
        this.fName = first_name;
    }

    public String getLast_name() {
        return lName;
    }

    public void setLast_name(String family_name) {
        this.lName = family_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

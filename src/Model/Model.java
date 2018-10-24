package Model;

import Controller.DBconnection;

/**
 * Created by Maor on 10/24/2018.
 */
public class Model {
    private DBconnection dBconnection;

    public Model(){
        dBconnection = new DBconnection();
    }
}

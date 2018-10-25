package Controller;

import Model.*;
/**
 * Created by Maor on 10/25/2018.
 */
public abstract class AController {
    protected static Model myModel = new Model();

    public static String getCurrentUser() {
        return myModel.getCurrentuser();
    }

}

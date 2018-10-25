package Controller;

/**
 * Created by Maor on 10/25/2018.
 */
public class DeleteController extends AController{

    public void deleteUser(String username){
        myModel.deleteUser(username);
    }
}

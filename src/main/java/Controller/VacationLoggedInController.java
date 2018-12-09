package Controller;

public class VacationLoggedInController extends AController {
    public void logout() {
        myModel.setCurrent_user(null);
    }
}

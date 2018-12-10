package Model;

/**
 * Created by Maor on 12/10/2018.
 */
public class Payment {
    String method;
    String buyer;
    int id;
    Vacation vacation;

    public Payment(Vacation vacation, String method, String buyer) {
        this.vacation = vacation;
        this.method = method;
        this.buyer = buyer;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

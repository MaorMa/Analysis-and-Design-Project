package Model;

public class Trade {
    public Vacation offerForId;
    public Vacation offerId;

    public Trade(Vacation offerId,Vacation offerForId) {
        this.offerForId = offerForId;
        this.offerId = offerId;
    }
}

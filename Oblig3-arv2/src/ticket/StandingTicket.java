package ticket;

/**
 * STandTicket.
 */
public class StandingTicket extends Ticket {

    private int place;
    public StandingTicket(String tribuneName, int price, int place) {
        super(tribuneName, price);
        this.place = place;
    }

    @Override
    public String toString() {
        return super.toString() + " Place: " + place;
    }
}

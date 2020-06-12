package tribune;

import ticket.StandingTicket;
import ticket.Ticket;

public class Stand extends Tribune {

    private int noSoldTickets;

    public Stand(String tribuneName, int capacity, int price, int noSoldTickets) {
        super(tribuneName, capacity, price);
        this.noSoldTickets = noSoldTickets;
    }

    public int findNoSoldTickets() {
        return this.noSoldTickets;
    }

    @Override
    public Ticket[] buyTicket(int noTickets) {
        if(noTickets < 0) {
            return new Ticket[0];
        }
        Ticket[] tickets = new Ticket[noTickets];
        if(noTickets <= this.getCapacity() && noTickets > 0) {
            for(int i = 0; i < tickets.length; i++) {
                tickets[i] = new StandingTicket(this.getTribuneName(), this.getPrice(), noSoldTickets);
                noSoldTickets++;
            }
        }
        return tickets;
    }

    @Override
    public Ticket[] buyTicket(String[] names) {
        if(names == null || names.length <= 0) {
            return new Ticket[0];
        }
        Ticket[] tickets = new Ticket[names.length];
        if(names.length <= this.getCapacity() && names.length > 0) {
            for(int i = 0; i < tickets.length; i++) {
                tickets[i] = new StandingTicket(this.getTribuneName(), this.getPrice(), noSoldTickets);
            }
        }
        return tickets;
    }

    @Override
    public String toString() {
        return "StandingTribune{" +
                super.toString() +
                '}';
    }
}

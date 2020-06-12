package tribune;

import ticket.SittingTicket;
import ticket.Ticket;


public class Sit extends Tribune {

    private int[] noBusy; // er hvor mange plasser som er solgt per rad.
    private int seatsPrRow;
    private int rows;


    public Sit(String tribuneName, int capacity, int price, int rows) {
        super(tribuneName, capacity, price);
        seatsPrRow = capacity/rows;
        this.rows = rows;
        noBusy = new int[rows];
    }

    public int[] getNoBusy() {
        return noBusy;
    }

    public int getRows() {
        return rows;
    }


    public int findNoSoldTickets() {
        int sum = 0;
        for(int i = 0; i < noBusy.length; i++) {
            sum += noBusy[i];
        }
        return sum;
    }

    @Override
    public Ticket[] buyTicket(int noTickets) {
        if(noTickets <= 0) {
            return new Ticket[0];
        }
        Ticket[] tickets = new Ticket[noTickets];
        int noSoldTickets = findNoSoldTickets();
        int available = 0;
        for(int i = 0; i < noBusy.length; i++) {
            available = seatsPrRow-noBusy[i];
            if(noTickets <= available) {
                for(int j = 0; j < tickets.length; j++) {
                    tickets[j] = new SittingTicket(this.getTribuneName(), this.getPrice(), i+1, noSoldTickets+1);
                    noBusy[i]++;
                    noSoldTickets++;
                }
                return tickets;
            }
        }
        return null;
    }

    public Ticket[] buyTicket2(String[] names) {
        if(names == null || names.length <= 0) {
            return new Ticket[0];
        }
        Ticket[] tickets = new Ticket[names.length];
        int noSoldTickets = findNoSoldTickets();
        int available = 0, i = 0, j = 0;
        int count = names.length;
        boolean finish = false;
        while(i < noBusy.length && !finish) {
            available = seatsPrRow-noBusy[i];
            while(names.length <= available && count > 0) {
                tickets[j] = new SittingTicket(this.getTribuneName(), this.getPrice(), i+1, noSoldTickets+1);
                noBusy[i]++;
                noSoldTickets++;
                j++;
                count--;
                if(count == 0) finish = true;
            }
            i++;
        }
        return tickets;
    }

    @Override
    public Ticket[] buyTicket(String[] names) {
        if(names == null || names.length <= 0) {
            return new Ticket[0];
        }
        Ticket[] tickets = new Ticket[names.length];
        int noSoldTickets = findNoSoldTickets();
        int available = 0;
        for(int i = 0; i < noBusy.length; i++) {
            available = seatsPrRow-noBusy[i];
            if(names.length <= available) {
                for(int j = 0; j < tickets.length; j++) {
                    tickets[j] = new SittingTicket(this.getTribuneName(), this.getPrice(), i+1, noSoldTickets+1);
                    noBusy[i]++;
                    noSoldTickets++;
                }
                return tickets;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "SittingTribune{" +
                "seatsPrRow=" + seatsPrRow + " " +
                super.toString() +
                '}';
    }
}

package tribune;

import ticket.SittingTicket;
import ticket.Ticket;

import java.util.Arrays;

public class VIP extends Sit {

    private String[][] spectator; //tilskuere: antall rader * antall plasser per rad
    private int seatsPrRow;
    private int noTicketsSold;
    private int[] noSoldTicketsRow;

    public VIP(String tribuneName, int capacity, int price, int rows) {
        super(tribuneName, capacity, price, rows);
        seatsPrRow = capacity/rows;
        spectator = new String[rows][seatsPrRow];
        noSoldTicketsRow = new int[this.getRows()];
    }

    public String[][] getSpectator() {
        return spectator;
    }

    @Override
    public int findNoSoldTickets() {
        for(int i = 0; i < spectator.length; i++) {
            for(int j = 0; j < spectator[i].length; j++) {
                if(spectator[i][j] != null) {
                    noTicketsSold++;
                }
            }
        }
        return noTicketsSold;
    }

    public int findRow(int seats) {
        int available = 0;
        for(int i = 0; i < spectator.length; i++) {
            //int count = (int) Arrays.stream(spectator[i]).filter(a -> a == null).count();
            for(int j = 0; j < spectator[i].length; j++) {
                if(spectator[i][j] == null) {
                    available++;
                }
            }
            if(seats <= available) return i;
        }
        return -1;
    }

    public int findPlace(String name, int row) {
        for(int j = 0; j < spectator[row].length; j++) {
            if(spectator[row][j] == null) {
                spectator[row][j] = name;
                return j;
            }
        }
        return -1;
    }


    @Override
    public Ticket[] buyTicket(String[] names) {
        if(names == null || names.length == 0) {
            return null;
        }
        int noSeats = names.length;
        int row = findRow(noSeats);
        if(row > -1) {
            Ticket[] tickets = new Ticket[noSeats];
            for(int i = 0; i < noSeats; i++) {
                int place = findPlace(names[i], row);
                tickets[i] = new SittingTicket(this.getTribuneName(), this.getPrice(), row+1, place+1);
            }
            return tickets;
        }
        return null;
    }




    @Override
    public String toString() {
        return "VIP{" +
                "seatsPrRow=" + seatsPrRow + " " +
                "Tribunename: " + this.getTribuneName() + " Capacity: " + this.getCapacity() + " Price: " + this.getPrice() +
                " NumberSoldTickets: " + findNoSoldTickets() +
                " Income: " + this.findIncome() +
                '}';
    }
}

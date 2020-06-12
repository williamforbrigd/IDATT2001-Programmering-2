package tribune;

import ticket.Ticket;

public abstract class Tribune {
    private final String tribuneName;
    private final int capacity;
    private final int price;

    public Tribune(String tribuneName, int capacity, int price) {
        this.tribuneName = tribuneName;
        this.capacity = capacity;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public String getTribuneName() {
        return tribuneName;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract int findNoSoldTickets();

    public double findIncome() {
        return this.getPrice()*findNoSoldTickets();
    }

    public abstract Ticket[] buyTicket(int noTickets);

    public abstract Ticket[] buyTicket(String[] names);


    public String toString() {
        return "Tribunename: " + tribuneName + " Capacity: " + capacity + " NumberSoldTickets: " + findNoSoldTickets() + " Income: " + findIncome();
    }
}

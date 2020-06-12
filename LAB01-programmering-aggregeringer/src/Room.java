import java.util.ArrayList;

public class Room {

    private int roomNumber;
    private ArrayList<Reservation> reservations;
    private int size;

    public Room(int roomNumber,int size) {
        this.roomNumber = roomNumber;
        reservations = new ArrayList<>();
        this.size = size;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAvailable(Period fraTid, Period tilTid) {
        if(fraTid == null || tilTid == null) {
            return false;
        }
        for(int i = 0; i < reservations.size(); i++) {
            if(reservations.get(i).overlapp(fraTid, tilTid)) {
                return false;
            }
        }
        return true;
    }

    public boolean registerReservation(Period fraTid, Period tilTid, String name, String tlf, int numberPersons) {
        if(fraTid == null || tilTid == null || name == null || tlf == null) {
            return false;
        } else if(isAvailable(fraTid, tilTid) && numberPersons <= size) {
            reservations.add(new Reservation(fraTid, tilTid, new Customer(name, tlf)));
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String println = "";
        println += "Room: " + roomNumber + " " + " " + "Size: " + size + "\nReservations:\n";
        for(Reservation reservation : reservations) {
            println += reservation.toString() + "\n";
        }
        return println;
    }

    public static void main(String[]args) {
        Room room = new Room(7, 10);
        if(room.registerReservation(new Period(200301201200L), new Period(200301201300L), "William", "41744440",9)) {
            System.out.println("Room registered successfully");
        } else {
            System.out.println("Room not");
        }

        System.out.println(room.toString());
    }
}

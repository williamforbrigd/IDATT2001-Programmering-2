import java.util.ArrayList;

public class ConferenceCenter {

    private ArrayList<Room> rooms;

    public ConferenceCenter() {
        rooms = new ArrayList<>();
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public boolean reserveRoom(Period from, Period to, int numberPersons, String name, String tlf) {
        if(from != null && to != null && numberPersons != 0 && name != null && tlf != null) {
            for(int i = 0; i < rooms.size(); i++) {
                rooms.get(i).registerReservation(from, to, name, tlf, numberPersons);
                return true;
            }
        }
        return false;
    }

    public boolean registerRoom(int roomNumber, int size) {
        if(roomNumber != 0 && size != 0) {
            rooms.add(new Room(roomNumber, size));
            return true;
        }
        return false;
    }

    public int findNoRooms() {
        return rooms.size();
    }

    public Room findRoomIndex(int index) {
        return (index >= 0 && index < rooms.size()) ? rooms.get(index) : null;
    }

    public Room findRoomNumber(int roomNumber) {
        return rooms.stream().filter(r-> r.getRoomNumber() == roomNumber).findAny().get();
    }

    public String toString() {
        String println = "";
        println += "Conferencecenter: \n";
        for(Room room : rooms) {
            println += room.toString() + "\n";
        }
        return println;
    }

    public static void main(String[]args) {
        //test this class
        ConferenceCenter center = new ConferenceCenter();
        if(center.registerRoom(7,7)) {
            System.out.println("Room registered");
        } else {
            System.out.println("Room not registered");
        }
        if(center.reserveRoom(new Period(200301201200L), new Period(200301201300L),6, "William", "41744440" )) {
            System.out.println("Room reserved");
        } else {
            System.out.println("Room not reserved");
        }

        System.out.println(center.toString());
        System.out.println(center.findRoomIndex(0).toString());
        System.out.println(center.findNoRooms());
        System.out.println(center.findRoomNumber(7).toString());
        }
}

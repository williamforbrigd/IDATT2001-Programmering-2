import java.util.Scanner;

public class Client {
    private final static int REGSITER_ROOM = 1;
    private final static int RESERVE_ROOM = 2;
    private final static int PRINT_ROOMS = 3;
    private final static int EXIT = 4;

    private int getMenuCoice() {
        int menuChoice = 0;

        System.out.println("1: Register room");
        System.out.println("2: Reserve room");
        System.out.println("3: Print all rooms");
        System.out.println("4: Exit");

        Scanner sc = new Scanner(System.in);

        if(sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("Choose a number");
        }
        return menuChoice;
    }

    private void start() {
        boolean finished = false;
        ConferenceCenter center = new ConferenceCenter();
        Scanner sc = new Scanner(System.in);

        while (!finished) {
            int menuChoice = getMenuCoice();
            switch (menuChoice) {
                case REGSITER_ROOM:
                    System.out.println("Room number: "); int roomNumber = sc.nextInt();
                    System.out.println("Size: "); int size = sc.nextInt();
                    if(center.registerRoom(roomNumber,size)) {
                        System.out.println("Room registered");
                    } else {
                        System.out.println("Room not registered");
                    }
                    break;
                case RESERVE_ROOM:
                    //System.out.println("From: "); long from = sc.nextLong();
                    //System.out.println("To: "); long to = sc.nextLong();
                    System.out.println("Persons: ");int persons = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Name: "); String name = sc.nextLine();
                    System.out.println("Phone number: "); String tlf = sc.nextLine();
                    if(center.reserveRoom(new Period(200301201200L), new Period(200301201300L),persons, name, tlf)) {
                        System.out.println("Room reserved");
                    } else {
                        System.out.println("Room not reserved");
                    }
                    break;

                case PRINT_ROOMS:
                    System.out.println(center.toString());
                    break;

                case EXIT:
                    System.out.println("Application is exiting");
                    finished = true;
                    break;

                default:
                    System.out.println("Enter one of the numbers");
                    break;
            }
        }
    }

    public static void main(String[]args) {
        Client client = new Client();
        client.start();
    }
}

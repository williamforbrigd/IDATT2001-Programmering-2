package model;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBookFileHandlerTest {
    private static final String CSV_DELIMITER = ";";

    private static final Logger logger = Logger.getLogger(AddressBookFileHandlerTest.class.getName());

    private AddressBookFileHandlerTest() {}

    /**
     * Eksporterer addresseboken til en .csv fil.
     * @param addressBook addresseboken som skal eksporteres
     * @param file files som addresseboken skal eksporteres til
     * @throws IOException hvis filen eksisterer men er et directory istendenfor en vanlig fil.
     * Eller hvis filen ikke eksisterer, men kan ikke lages, eller kan ikke bli åpnet av en eller
     * annen grunn.
     */

    public static void exportToCsv(AddressBook addressBook, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Name" + CSV_DELIMITER + "Phone" + CSV_DELIMITER + "Address");

        for(ContactDetails contact : addressBook.getAllContacts()) {
            printWriter.println(contact.getName() + CSV_DELIMITER + contact.getPhone() +
                                                    CSV_DELIMITER + contact.getAddress());
        }
        printWriter.close();
    }

    /**
     * Importerer data fra en .csv-fil til en eksisterende addressebok som er gitt som parameter. .
     * @param addressBook den eksisterende addresseboken.
     * @param file .csv-filen som inneholder all dataen.
     * @throws IOException hvis en I/O error skjer når filen åpnes.
     */

    public static void importFromCsv(AddressBook addressBook, File file) throws IOException {
        try(BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            boolean isTitleLine = true;
            String lineOfText = reader.readLine();
            while(lineOfText != null) {
                if(!isTitleLine) {
                    ContactDetails contact = parseStringAsContact(lineOfText);
                    addressBook.getAllContacts().add(contact);
                } else {
                    isTitleLine = false;
                }
                lineOfText = reader.readLine();
            }
        }
    }

    /**
     * Hjelpemetode som skal gjøre om en tekststreng til en instanse av ContactDetails
     * @param line som er tekstrengen som skal brukes til å opprette en contact. Strengen skal inneholde
     *             CSV separerte felter som skiller dataen fra hverandre.
     * @return contact som er en instanse av ContactDetails laget av linjen som er sendt inn som parameter.
     * @throws ContactDetailsFormatException hvis strengen ikke kan gjøres om til en instanse av ContactDetails.
     */

    private static ContactDetails parseStringAsContact(String line) {
        ContactDetails contact = null;
        if(line != null) {
            String[] splitString = line.split(CSV_DELIMITER);
            if(splitString.length == 3) {
                contact = new ContactDetails(splitString[0], splitString[1], splitString[2]);
            } else {
                throw new ContactDetailsFormatException();
            }
        }
        return contact;
    }

    /**
     * Lagrer alle objektene i en addresse bok til en file som er sendt inn som parameter ved hjelp av
     * objekt serialisering. Serializable er et marker interface, og gjør igenting utenom å indikere at denne
     * klassen skal serialiseres. Er et rammeverk for lagrin av objekter til fil. Benytter strømmen
     * ObjectOutputStream for å skrive objekter til fil.
     * @param addressBook addresseboken som skal lagres.
     * @param file som kontaktene skal lagres til.
     * @throws IOException hvis en I/O feil skjer når det skrives til filen.
     */

    public static void saveToFile(AddressBook addressBook, File file) throws IOException {
        try(OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(addressBook);
        }
    }

    /**
     * Returnerer en addressebok fra en gitt fil ved bruk av objekt serialisering.
     * @param file filen som addresseboken skal opprettes av.
     * @return en instans av addresseboken med alle kontaktene i den gitte filen. Eller en tom addressebok
     * hvis filen ikke kan leses av en elle annen grunn.
     * @throws IOException hvis filen ikke kan leses.
     * @throws ClassNotFoundException
     */

    public static AddressBook loadFromFile(File file) {
        try(InputStream inputStream = Files.newInputStream(file.toPath())) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (AddressBook) objectInputStream.readObject();
        } catch(IOException | ClassNotFoundException e) {
            logger.log(Level.INFO, "Could not open file " +
                    file.getName() + ". An empty addressbook was returned");
            return new AddressBookDBHandler();
        }
    }
}

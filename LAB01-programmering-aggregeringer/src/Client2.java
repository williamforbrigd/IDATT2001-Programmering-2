

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Klientprogram iht. malen i oppgaveteksten. EL.
 */

class Client2 {
    public static void main(String[] args) throws IOException {
        ConferenceCenter senter = new ConferenceCenter();
        KonfBGS bgs = new KonfBGS(senter);
        bgs.registerAllRooms();
        bgs.registerAllReservations();
        bgs.printAllInfo();
        bgs.searchInfo();
    }
}

/**
 * Klasse som inneholder metoder som kan brukes av klientprogrammet. En metode
 * pr. punkt i oppgaveteksten.
 */
class KonfBGS {
    private final ConferenceCenter senter;

    public KonfBGS(ConferenceCenter senter) {
        this.senter = senter;
    }

    /**
     * Metoden går i løkke og registrerer rommene.
     * @throws IOException
     */
    public void registerAllRooms() throws IOException {
        int antallRom = (int) lesHeltall("Hvor mange rom? ");
        int antallRegistrert = 0;
        while (antallRegistrert < antallRom) {
            int romnr = (int) lesHeltall((antallRegistrert + 1) + ". Oppgi romnr: ");
            int størrelse = (int) lesHeltall((antallRegistrert + 1) + ". St�rrelse: ");
            if (senter.registerRoom(romnr, størrelse)) {
                System.out.println("Rom er registrert.");
                antallRegistrert++;
            } else {
                System.out.println("Feil i dataene, eller rom med dette nummer er registrert fra f�r.");
            }
        }
    }

    /**
     * Metoden g�r i l�kke og registrerer reservasjonene.
     * @throws IOException
     */
    public void registerAllReservations() throws IOException {
        int antRes = (int) lesHeltall("Hvor mange reservasjoner? ");
        int antResRegistrert = 0;
        while (antResRegistrert < antRes) {
            long startTime = lesHeltall("Starttid (����mmddttmm): ");
            long endTime = lesHeltall("Slutt-tid (����mmddttmm): ");
            int numberOfPersons = (int) lesHeltall("Hvor mange personer? ");
            String name = lesTekst("Kundens navn: ");
            String tel = lesTekst("Kundens telefon-nummer: ");
            boolean rom = senter.reserveRoom(new Period(startTime), new Period(endTime), numberOfPersons, name, tel);
            if (rom) {
                System.out.println("Rom nr " + rom + " er reservert.");
                antResRegistrert++;
            } else {
                System.out.println("Feil i dataene, eller ingen rom ledig.");
            }
        }
    }

    /**
     * Metoden skriver ut alle registrerte data i kommandovinduet.
     */
    public void printAllInfo() {
        for (int i = 0; i < senter.findNoRooms(); i++) {
            System.out.println(senter.findRoomIndex(i));
        }
    }

    /**
     * Metoden lar brukeren s�ke ut informasjon gitt romnummer.
     * @throws IOException
     */
    public void searchInfo() throws IOException {
        int nr = (int) lesHeltall("Oppgi romnr, avslutt med negativt tall: ");
        while (nr >= 0) {
            Room etRom = senter.findRoomNumber(nr);
            if (etRom != null) {
                System.out.println("Rom funnet, her er all info.:\n " + etRom.toString());
            } else {
                System.out.println("Ikke funnet rom med nr " + nr);
            }
            nr = (int) lesHeltall("Oppgi romnr, avslutt med negativt tall: ");
        }
    }

    /**
     * Hjelpemetoder for innlesing av tekst og store heltall
     * @throws IOException
     */
    private String lesTekst(String ledetekst) throws IOException {
        BufferedReader br = null;

        System.out.println(ledetekst);
        br = new BufferedReader(new InputStreamReader(System.in));
        String tekst = br.readLine();

        while (tekst == null || tekst.trim().equals("")) {
            System.out.println("Du m� oppgi data.");
            br = new BufferedReader(new InputStreamReader(System.in));
            tekst = br.readLine();
        }
        return tekst.trim();
    }

    public long lesHeltall(String ledetekst) throws IOException {
        long tall = 0L;
        boolean ok = false;
        do {
            String lestTekst = lesTekst(ledetekst);
            try {
                tall = Long.parseLong(lestTekst);
                ok = true;
            } catch (NumberFormatException e) { // h�ndterer ugyldig heltall
                System.out.println("Ugyldig heltall.\n");
            }
        } while (!ok);
        return tall;
    }
}

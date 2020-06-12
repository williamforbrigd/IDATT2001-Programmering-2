import java.io.IOException;
import java.util.logging.*;

/**
 * Klassen MyLogger er bygget på designmønsteren Singleton, i den forstand at kun en logger er nødvendig i dette
 * tilfellet. På den måten, har klassen et statisk medlem, som er loggeren. Konstruktøren er satt til
 * private for å sikre at ingen instanser av denne klassen blir oprettet fra utsiden.
 */
public class MyLogger {

    private static Logger logger;
    private static Handler handler;

    /**
     * Klassen er konstruert i henhold til designmønsteret Singleton. Derfor er
     * konstruktøren satt til private for hindre at instanser av denne klassen opprettes fra
     * utsiden.
     */
    private MyLogger() {}

    /**
     * Her brukes lazy initialized singleton design mønster i den forstand at loggeren
     * initialiseres gjennom getLogger()-metoden og ikke når den deklareres.
     * @return logger instansen av loggeren.
     */
    public static Logger getLogger() {
        try {
            if(logger == null) {
                logger = Logger.getLogger(MyLogger.class.getName());

                //Lager en filehandler som skriver ut all informasjonen til en .log fil.
                handler = new FileHandler("MyLogger.txt", true);

                //en simpleformatter som endrer all log formatet fra XML til et format som er leselig for mennesker.
                handler.setFormatter(new SimpleFormatter());

                //Setter hvilke tilfeller som skal bli logget eller ikke.
                handler.setLevel(Level.ALL);

                logger.addHandler(handler);
                logger.setLevel(Level.ALL);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

    /**
     * Lukker handleren etter at noe er blitt logget. Dette er nødvendig for
     * å sikre seg at ikke flere .log filer blir laget.
     */

    public static void closeHandler() {
        handler.close();
    }

}

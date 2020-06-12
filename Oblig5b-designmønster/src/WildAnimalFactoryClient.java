import java.time.LocalDate;
import java.util.logging.Level;

public class WildAnimalFactoryClient {
    public static void main(String[]args) {

        testWildAnimalFactorySingleton();

    }

    private static void testWildAnimalFactory() {
        WildAnimalFactory factory = new WildAnimalFactory();

        factory.getAnimals().add(factory.newMaleBear
                (LocalDate.of(2014,3,7), "Alex",
                        LocalDate.of(2012,3,6), "Innhegning 3"));

        factory.getAnimals().add(factory.newFemaleWolf
                (LocalDate.of(2015,2,6), "Ulla",
                        LocalDate.of(2015,4,29),
                        "Innhegning 2",10));

        factory.getAnimals().add(factory.newMaleWolf
                (LocalDate.of(2012,3,6),"Dorthea",
                        LocalDate.of(2010,3,7), "Innhegning 4"));

        try {
            factory.getAnimals().add(factory.newFemaleWolf(null, null, null, null,0));
        } catch(IllegalArgumentException e) {
            MyLogger.getLogger().log(Level.ALL, e.getMessage());
            MyLogger.closeHandler();
        }

        factory.getAnimals().forEach(s -> System.out.println(s.printInfo()));
    }

    private static void testWildAnimalFactorySingleton() {
        WildAnimalFactorySingleton factorySingleton = WildAnimalFactorySingleton.getInstance();

        factorySingleton.newMaleBear(LocalDate.of(2014,3,7), "Alex",
                LocalDate.of(2012,3,6), "Innhegning 3");

        factorySingleton.newFemaleWolf(LocalDate.of(2015,2,6), "Ulla",
                LocalDate.of(2015,4,29),
                "Innhegning 2",10);

        factorySingleton.newMaleWolf(LocalDate.of(2012,3,6),"Dorthea",
                LocalDate.of(2010,3,7), "Innhegning 4");

        WildAnimalFactorySingleton.getAnimals().forEach(s -> System.out.println(s.printInfo()));
    }
}

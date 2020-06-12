
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * En factory klasse som baserer seg på factory design mønster.
 * Dermed kan sub-klassene som er FemaleIndividual.java og MaleIndividual.java bestemme hvilke objekter som
 * skal lages. Klassen er basert på prinsippet om "loose coupling" i den forstand at koden samhandler med
 * grensesnittet ScandinavianWildAnimal, og den vil dermed fungerere med alle klasser som implementerer
 * grensesnittet.
 * A factory class that is based on the factory design pattern. In that way, the sub-classes which is FemaleIndividual.java
 * and MaleIndividual.java can decide what objects that can be created. The class is coded in regards to the principal
 * loose coupling, in the way that the class is using the interface ScandinavialWildAnimal to create objects,
 * and then this class works for all classes that implements the interface.
 *
 * The class is also based on the Singleton design pattern, in the way that the class has only one instance.
 * At the same time, the constructor is set to private to ensure that no objects of this class are created
 * from the outside. All three of the factory methods are static, and they instantiate the static member of this class.
 *
 */

public class WildAnimalFactorySingleton {

    private static WildAnimalFactorySingleton instance;
    private ScandinavianWildAnimal animal;


    private static ArrayList<ScandinavianWildAnimal> animals = new ArrayList<>();

    private WildAnimalFactorySingleton() {}

    public static WildAnimalFactorySingleton getInstance() {
        if(instance == null) {
            instance = new WildAnimalFactorySingleton();
        }
        return instance;
    }

    public static ArrayList<ScandinavianWildAnimal> getAnimals() {
        return animals;
    }

    public ScandinavianWildAnimal newMaleBear(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address) throws IllegalArgumentException {
        if(arrivalDate == null || name == null || dateOfBirth == null || address == null) {
            throw new IllegalArgumentException("One of the fields cannot be null or invalid");
        }
        if(instance != null) {
            animal = new MaleIndividual("Brunbjørn", "Ursus arctos",
                    "Ursidae",
                    arrivalDate,
                    name,
                    dateOfBirth,
                    true, address);
            animals.add(animal);
            return animal;
        }
        return null;
    }

    public ScandinavianWildAnimal newFemaleWolf(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address, int noLitters) throws IllegalArgumentException {
        if(arrivalDate == null || name == null || dateOfBirth == null || address == null) {
            throw new IllegalArgumentException("One of the fields cannot be null or invalid");
        }
        if(instance != null) {
            animal = new FemaleIndividual("Ulv",
                    "Canis lupus",
                    "Canidae",
                    arrivalDate,
                    name,
                    dateOfBirth,
                    true,
                    address,
                    noLitters);
            animals.add(animal);
            return animal;
        }
        return null;
    }

    public ScandinavianWildAnimal newMaleWolf(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address) throws IllegalArgumentException {
        if(arrivalDate == null || name == null || dateOfBirth == null || address == null) {
            throw new IllegalArgumentException("One of the fields cannot be null or invalid");
        }
        if(instance != null) {
            animal = new MaleIndividual("Ulv",
                    "Canis lupus",
                    "Canidae",
                    arrivalDate,
                    name,
                    dateOfBirth,
                    true,
                    address);
            animals.add(animal);
            return animal;
        }
        return null;
    }
}
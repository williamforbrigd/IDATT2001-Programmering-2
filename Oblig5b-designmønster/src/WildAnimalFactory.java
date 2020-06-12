import java.time.LocalDate;
import java.util.ArrayList;

/**
 * En factory klasse som baserer seg på factory design mønster.
 * Dermed kan sub-klassene som er FemaleIndividual.java og MaleIndividual.java bestemme hvilke objekter som
 * skal lages. Klassen er basert på prinsippet om "loose coupling" i den forstand at koden samhandler med
 * grensesnittet ScandinavianWildAnimal, og den vil dermed fungerere med alle klasser som implementerer
 * grensesnittet.
 */

public class WildAnimalFactory {

    private ArrayList<ScandinavianWildAnimal> animals;

    public WildAnimalFactory() {
        animals = new ArrayList<>();
    }

    public ArrayList<ScandinavianWildAnimal> getAnimals() {
        return this.animals;
    }

    public ScandinavianWildAnimal newMaleBear(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address) throws IllegalArgumentException {
        if(arrivalDate == null || name == null || dateOfBirth == null || address == null) {
            throw new IllegalArgumentException("One of the fields cannot be null or invalid");
        }
        return new MaleIndividual("Brunbjørn", "Ursus arctos",
                "Ursidae",
                arrivalDate,
                name,
                dateOfBirth,
                true, address);
    }

    public ScandinavianWildAnimal newFemaleWolf
            (LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address, int noLitters) throws IllegalArgumentException {
        if(arrivalDate == null || name == null || dateOfBirth == null || address == null) {
            throw new IllegalArgumentException("One of the fields cannot be null or invalid");
        }
        return new FemaleIndividual("Ulv",
                    "Canis lupus",
                    "Canidae",
                    arrivalDate,
                    name,
                    dateOfBirth,
                    true,
                    address,
                    noLitters);
    }

    public ScandinavianWildAnimal newMaleWolf
            (LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address) throws IllegalArgumentException {
        if(arrivalDate == null || name == null || dateOfBirth == null || address == null) {
            throw new IllegalArgumentException("One of the fields cannot be null or invalid");
        }
        return new MaleIndividual("Ulv",
                    "Canis lupus",
                    "Canidae",
                    arrivalDate,
                    name,
                    dateOfBirth,
                    true,
                    address);

    }
}

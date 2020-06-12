package compare;

import animal.Animal;

import java.util.Comparator;

public class SortByID implements Comparator<Animal> {

    @Override
    public int compare(Animal a1, Animal a2) {
        return Integer.compare(a1.getAnimalID(), a2.getAnimalID());
    }
}

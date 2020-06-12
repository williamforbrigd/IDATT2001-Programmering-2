package animal;

public abstract class Animal implements Comparable<Animal> {
    public final String name;
    public final int animalID;

    public Animal(String name, int code) {
        this.name = name;
        this.animalID = code;
    }

    public int getAnimalID() {
        return animalID;
    }

    public int compareTo(Animal a) {
        return Integer.compare(this.getAnimalID(), a.getAnimalID());
    }

    @Override
    public String toString() {
        return "[name=" + name + ", ID=" + animalID + "]";
    }
}

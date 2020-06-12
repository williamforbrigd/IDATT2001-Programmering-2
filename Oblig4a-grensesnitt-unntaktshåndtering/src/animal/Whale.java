package animal;

import animal.Mammal;
import interfaces.IJumpable;
import interfaces.ISwimmable;

public class Whale extends Mammal implements IJumpable, ISwimmable {

    public Whale(String name, int animalID) {
        super(name, animalID);
    }

    public boolean jump() {
        System.out.println("jump");
        return true;
    }

    public boolean swim() {
        System.out.println("swim");
        return true;
    }

    @Override
    public String toString() {
        return "Whale " + super.toString();
    }
}

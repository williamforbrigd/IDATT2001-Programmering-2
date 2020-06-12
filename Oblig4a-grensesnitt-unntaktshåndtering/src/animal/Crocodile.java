package animal;

import interfaces.ISwimmable;
import interfaces.IWalkable;

public class Crocodile extends Oviparous implements ISwimmable, IWalkable {
    public Crocodile(String name, int animalID) {
        super(name, animalID);
    }

    @Override
    public boolean swim() {
        System.out.println("swim");
        return true;
    }

    @Override
    public boolean walk() {
        System.out.println("walk");
        return true;
    }

    @Override
    public String toString() {
        return "Crocodile " + super.toString();
    }
}

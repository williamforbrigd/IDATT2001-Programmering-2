package animal;

import animal.Oviparous;
import interfaces.IFlyable;
import interfaces.IWalkable;

public class Pelican extends Oviparous implements IWalkable, IFlyable {
    public Pelican(String name, int animalID) {
        super(name, animalID);
    }

    @Override
    public boolean fly() {
        System.out.println("fly");
        return true;
    }

    @Override
    public boolean walk() {
        System.out.println("walk");
        return true;
    }

    @Override
    public String toString() {
        return "Pelican " + super.toString();
    }
}

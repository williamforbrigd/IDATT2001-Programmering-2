package animal;

import interfaces.IFlyable;

public class Bat extends Mammal implements IFlyable {

    public Bat(String name, int animalID) {
        super(name, animalID);
    }

    @Override
    public boolean fly() {
        System.out.println("fly");
        return true;
    }

    @Override
    public String toString() {
        return "Bat " + super.toString();
    }
}

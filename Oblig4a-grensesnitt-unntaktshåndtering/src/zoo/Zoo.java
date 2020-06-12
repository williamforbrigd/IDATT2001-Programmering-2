package zoo;

import animal.Animal;
import animal.Mammal;
import exceptions.ZooException;
import interfaces.IFlyable;
import interfaces.IJumpable;
import interfaces.IWalkable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Zoo {
    private final String name;
    private Collection<Animal> animals;

    public Zoo(String name) {
        this.name = name;
        animals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Collection<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Collection<Animal> animals) {
        this.animals.addAll(animals);
    }

    public Collection<Animal> findAllFlyers() {
        return animals.stream().filter(a -> a instanceof IFlyable).collect(Collectors.toList());
    }

    public void fly(Collection<Animal> flyers) {
        flyers.forEach(f -> {
            ((IFlyable)f).fly();
        });
    }

    public Collection<Animal> findWhales() {
        return animals.stream().filter(a -> a instanceof Mammal && a instanceof IJumpable)
                                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void jump(Collection<Animal> whales) {
        whales.forEach(m -> {
            ((IJumpable)m).jump();
        });
    }

    public void makeWalkersFly() {
        try {
            List<Object> walker =
                    animals.stream().filter(a -> a instanceof IWalkable)
                                    .collect(Collectors.toCollection(ArrayList::new));

            walker.forEach(w -> {
                ((IFlyable)w).fly();
            });

        } catch(ClassCastException e) {
            try {
                throw new ZooException("A walker is not an instance of a flyer");
            } catch(ZooException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String toString() {
        String println = "Total animals: " + animals.size() + "\n";
        for(Animal animal : animals) {
            println += animal.toString() + "\n";
        }
        return println;
    }

    public String toString(ArrayList<Animal> print) {
        String println = "Total animals: " + print.size() + "\n";
        for(Animal animal : print) {
            println += animal.toString() + "\n";
        }
        return println;
    }

}

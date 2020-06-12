package zoo;

import animal.*;
import compare.SortByID;
import exceptions.ZooException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ZooClient {
    public static void main(String[] args) {

        Zoo zoo = new Zoo("Kristiansand Dyrepark");

        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(new Crocodile("Crocodylus niloticus", 1001));
        animals.add(new Crocodile("Crocodylus niloticus", 1002));
        animals.add(new Crocodile("Crocodylus porosus", 1101));
        animals.add(new Crocodile("Crocodylus porosus", 1102));
        animals.add(new Pelican("Brown Pelican", 4001));
        animals.add(new Pelican("Dalmatian Pelican", 4101));


        animals.add(new Whale("Blue whale", 2001));
        animals.add(new Whale("Blue whale", 2002));
        animals.add(new Whale("Minke whale", 2101));
        animals.add(new Whale("Minke whale", 2102));
        animals.add(new Bat("Acerodon", 3001));
        animals.add(new Bat("Cistugo", 3002));
        zoo.setAnimals(animals);

        //System.out.println(zoo.toString());
        //System.out.println();


        /*
        zoo.makeWalkersFly();

        System.out.println();
        ArrayList<Animal> flyers = (ArrayList<Animal>) zoo.findAllFlyers();
        System.out.println(zoo.toString(flyers));
        zoo.fly(flyers);
        System.out.println();

        ArrayList<Animal> mammals = (ArrayList<Animal>) zoo.findWhales();
        System.out.println(zoo.toString(mammals));
        zoo.jump(mammals);
        System.out.println();

         */

        //Animals sorted by AnimalID with Comparator
        //Collections.sort(animals, Comparator.comparingInt(Animal::getAnimalID));
        //System.out.println(zoo.toString(animals));
        animals.stream().sorted(Comparator.comparingInt(Animal::getAnimalID)).forEach(s -> System.out.println(s));

        //Sort by comparator class
        //Collections.sort(animals, new SortByID());
        //System.out.println(zoo.toString(animals));
        //animals.stream().sorted(new SortByID()).forEach(s -> System.out.println(s));


        //Sort with Comparable
        //Collections.sort(animals);
        //System.out.println(zoo.toString(animals));



    }
}
package main;

import tribune.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[]args) {

        Stand stand = new Stand("santiago", 100, 50, 50);
        System.out.println(stand.toString());
        Stand stand1 = new Stand("old trafford", 150, 100, 70);
        System.out.println(stand1.toString());
        Sit sit = new Sit("bay hill", 50, 200, 5);
        System.out.println(sit.toString());
        VIP vip = new VIP("augusta", 200,250, 10);
        System.out.println(vip.toString());

        Tribune[] tribunes = {stand,stand1,sit,vip};
        String[] names = {"a","b","c","d","e","f","g"};
        String[] names2 = {"h","i","j","k","l","m","n"};



        for(int i = 0; i < tribunes.length; i++) {
            System.out.println(tribunes[i].findNoSoldTickets());
        }

        //Stand
        //System.out.println(Arrays.toString(stand.buyTicket(8)));

        //Sit
        //System.out.println(Arrays.toString(sit.buyTicket(names)));
        //System.out.println(Arrays.toString(sit.buyTicket(8)));
        //System.out.println(Arrays.toString(sit.buyTicket2(names)));

        //VIP
        System.out.println(Arrays.toString(vip.buyTicket(names)));
        System.out.println(Arrays.toString(vip.buyTicket(names2)));

        System.out.println(Arrays.deepToString(vip.getSpectator()));


        for(int i = 0; i < tribunes.length; i++) {
            System.out.println(tribunes[i].findNoSoldTickets());
        }


        //Arrays.sort(tribunes, Comparator.comparingDouble(Tribune::findIncome).reversed());


        Arrays.sort(tribunes, new SortByIncome().reversed());

        Arrays.stream(tribunes).forEach(s -> System.out.println(s.toString()));




    }
}

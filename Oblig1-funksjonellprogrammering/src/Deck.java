import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Deck {

    private ArrayList<Card> cards;
    private final char[] suit = {'S', 'H', 'D', 'C'};
    private final int face = 13;

    public Deck() {
        cards = new ArrayList<>();
        for(int i = 0; i < suit.length; i++) {
            for(int j = 0; j < face; j++) {
                cards.add(new Card(suit[i], j+1));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public Collection<Card> assign(int n) {
        if(n < 1 || n > 52) return null;
        ArrayList<Card> list = new ArrayList<>();
        int index = 0;
        while(--n >= 0) {
            index = new java.util.Random().nextInt(cards.size());
            list.add(cards.get(index));
        }
        return list;
    }

    public void printSpades(Collection<Card> assigned) {
        assigned.stream()
                .filter(c -> c.getSuit() == 'S')
                .collect(Collectors.toList())
                .forEach(c -> System.out.println(c.toString()));

    }

    public Collection<Card> collectHearts(Collection<Card> assigned) {
        return assigned.stream().filter(c -> c.getSuit() == 'H').collect(Collectors.toList());
    }

    public List<String> getColor(Collection<Card> assigned) {
        return assigned.stream().map(c -> c.getSuit() == 'H' || c.getSuit() == 'D' ? "red" : "black").collect(Collectors.toList());
    }

    public int getTotalValue() {
        //return cards.stream().map(Card::getFace).reduce((a,b)->a+b).get();
        return cards.stream().map(Card::getFace).reduce(0,(a,b)->a+b);
        //return cards.stream().map(Card::getFace).collect(Collectors.summingInt(Integer::intValue));
        //return cards.stream().map(Card::getFace).reduce(0,Integer::sum);
        //return cards.stream().mapToInt(x -> x.getFace()).sum();
        //return cards.stream().mapToInt(Card::getFace).sum();
    }

    public boolean findSpadeQueen(Collection<Card> assigned) {
        return assigned.stream().anyMatch(c -> c.getSuit() == 'S' && c.getFace() == 12);
    }


    /*
    public boolean isPokerFlush(Collection<Card> assigned) {
        List<String> getColor = getColor(assigned);
        if(getColor.size() != 5) {
            return false;
        } else {
            if(getColor.stream().allMatch(c -> c.equals("red"))) return true;
            else if(getColor.stream().allMatch(c -> c.equals("black"))) return true;
            else return false;
        }
    }

     */

    public Map count(Collection<Card> assigned) {
        List<Character> suits = assigned.stream().map(Card::getSuit).collect(Collectors.toList());
        Map<Character, Long> suitMap = new HashMap<>();
        for(int i = 0; i < suits.size(); i++) {
            if(!suitMap.containsKey(suits.get(i))) {
                suitMap.put(suits.get(i),1L);
            } else {
                Long count = suitMap.get(suits.get(i));
                suitMap.put(suits.get(i), count+1);
            }
        }
        return suitMap;
    }

    public Map isPokerFlush2(Collection<Card> assigned) {
        List<Character> suits = assigned.stream().map(Card::getSuit).collect(Collectors.toList());
        Map<Character,Long> suitMap = suits.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if(suitMap.values().stream().reduce(Math::max).get() == 5) {
            System.out.println("Flush!");
        }

        /*
        try {
            suitMap.values().stream().filter(a -> a == 5).findAny().get();
            System.out.println("Flush!");
        } catch(NoSuchElementException e) {}

         */

        return suitMap;
    }

    public void collectSuits(Collection<Card> assigned) {
        Collection<Character> suits = assigned.stream().map(Card::getSuit).collect(Collectors.toList());
        Map<Character, Long> mapSuits = suits.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        mapSuits.forEach((c,l)->System.out.println(c + " av " +l ));
    }

    public String toString(Collection print) {
        String println  = "";
        for(Object object : print) {
            println += object + "\n";
        }
        return println;
    }

    public int getSum() {
        int j = 0,i = 0, s = 0;
        while(j++ < 52) {
            i++;
            s+=i;
            if(i == 13) i = 0;
        }
        return s;
    }

    public static void main(String[]args) {
        Deck deck = new Deck();
        Collection<Card> assigned = deck.assign(15);
        System.out.println(deck.toString(assigned));
        deck.printSpades(assigned);

        System.out.println(deck.toString(deck.collectHearts(assigned)));
        System.out.println(deck.toString(deck.getColor(assigned)));
        System.out.println(deck.getTotalValue());
        System.out.println(deck.findSpadeQueen(assigned));

        //System.out.println(deck.isPokerFlush2(assigned));
        //deck.isPokerFlush2(assigned);


        //System.out.println(deck.getSum());
        //System.out.println(deck.toString(deck.getCards()));


        /*
        for(Object keys : deck.isPokerFlush2(assigned).keySet()) {
            System.out.println(keys.toString() + ":" + deck.isPokerFlush2(assigned).get(keys));
        }

         */
        System.out.println(deck.isPokerFlush2(assigned).toString());
        System.out.println(deck.count(assigned).toString());
        //if(deck.isPokerFlush(assigned)) System.out.println("Poker flush shameenr");

        deck.collectSuits(assigned);

    }
}

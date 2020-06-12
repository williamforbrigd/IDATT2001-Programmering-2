
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeckClient {

    public static void main(String[] args) {

        // Oppgave a
        Deck deck = new Deck();

        // Oppgave b
        Collection<Card> myCards = deck.assign(10);
        System.out.println("Cards generated: ");
        myCards.forEach(s -> System.out.println(s));

        // Oppgace c
        System.out.println("Spares: ");
        myCards.stream().filter(p -> p.getSuit() == 'S').collect(Collectors.toList())
                .forEach(s -> System.out.println(s));

        // Oppgave d
        System.out.println("Harts: ");
        Collection<Card> harts = myCards.stream().filter(p -> p.getSuit() == 'H').collect(Collectors.toList());
        harts.forEach(s -> System.out.println(s));

        // Oppgave e
        System.out.println("Kortfarge: ");
        List<Character> suits = new ArrayList<Character>();
        suits = myCards.stream().map(Card::getSuit).collect(Collectors.toList());
        suits.forEach(s -> System.out.println(s));

        // Kan ogs� gruppere disse (ikke del av oppgaven):
        suits.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((c, l) -> System.out.println(l + " av " + c));

        // Oppgave f
        int sumFace = myCards.stream().map(Card::getFace).reduce((a, b) -> a + b).get();
        System.out.println(sumFace);

        // Oppgave g
        boolean H12 = myCards.stream().anyMatch(p -> p.toString() == "H12");
        if (H12) {
            System.out.println("Spar dame finnes!");
        } else {
            System.out.println("Spar dame finnes ikke!");
        }

        // Oppgave h
        suits = myCards.stream().map(Card::getSuit).collect(Collectors.toList());
        Map<Character, Long> suitMap = suits.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (suitMap.values().stream().reduce(Math::max).get() == 5) {
            System.out.println("Flush!!");
        }

        // Alternativ:
        try {
            suitMap.values().stream().filter(a -> a == 5).findAny().get();
            System.out.println("Flush!!");
        } catch (NoSuchElementException e) {

        }
    }
}
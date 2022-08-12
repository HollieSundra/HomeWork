import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // i player
    // 1. Field/property declaration

    // 2. Constructor

    // 3. Public methods

    // 4. Private methods

    // 5. Private helper methods

    private ArrayList<Blackjack.Card> cards = new ArrayList<>();

    public int wallet;

    private String prefix;

    Player(String handName) {

        prefix = handName;
    }

    public void DisplayHand() {
        System.out.println(prefix + " : ");

        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i) + " ");
        }
        System.out.println();

        System.out.println("Total: " + getHandTotal());
    }

    public void addCard(Blackjack.Card card) {
        cards.add(card);
    }

    public int getHandTotal() {
        int handTotal = 0;

        for (int i = 0; i < cards.size(); i++) {
            Blackjack.Card card = cards.get(i);
            int cardValue = card.value;
            handTotal = handTotal + cardValue;
        }
        return handTotal;
    }

}




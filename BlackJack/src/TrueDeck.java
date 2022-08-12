import java.util.ArrayList;

public class TrueDeck implements IDeck {


    private ArrayList<String> deck = new ArrayList<String>();
    TrueDeck () {

        for (int i = 0; i < 4; i++) {

            deck.add("Two");
            deck.add("Three");
            deck.add("Four");
            deck.add("Five");
            deck.add("Six");
            deck.add("Seven");
            deck.add("Eight");
            deck.add("Nine");
            deck.add("Ten");
            deck.add("Jack");
            deck.add("Queen");
            deck.add("King");
            deck.add("Ace");

        }

    @Override
    public Blackjack.Card DrawRandomCard() {

            int value = (int) (Math.random() * 13) + 1;
            switch (value) {
                case 13:
                    return Blackjack.Card.King;
                case 12:
                    return Blackjack.Card.Queen;
                case 11:
                    return Blackjack.Card.Jack;

                default:
                    return Blackjack.Card.valueOf(value);
            }


            }
        }

    @Override
    public void TrashCard(Blackjack.Card card) {
        
    }
}



// Array list populate with ever possible care.
// Draw random gets number from index and remove from array list
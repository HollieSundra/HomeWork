import java.util.ArrayList;

/*
            ::::: Card Overview :::::
    Internally, cards are treated as an integer.
    1: Ace
    2-10: Actual cards, 2 thru 10
    11: Jack
    12: Queen
    13: King

    - Card numbers should not be directly treated as their score.
    - A king should still be worth 10, for instance.
    - Aces are always worth 11, unless causing a bust, then their worth is a 1.
    - The dealer should stay when they have a 17 or higher.
 */
public class Blackjack {

    // Hands store card numbers for a given player.
    private ArrayList<String> userHand = new ArrayList<>();
    private ArrayList<String> dealerHand = new ArrayList<>();

    // Triggers the start of a game loop.
    public void PlayGame() {
        System.out.println("Welcome to BlackJack.");

        // Get a random card and give it to the player.
        var randomCard = DrawRandomCard();
        userHand.add(randomCard);

        // Show the user their hand.
        DisplayHand(userHand);

        // Get a random card and give it to the dealer.
        dealerHand.add(DrawRandomCard());

        // Show the user the dealer's hand.
        DisplayHand(dealerHand);


        /*
            TODO: Write the game loop.
            Setup:
                User and dealer should both be given 2 cards to start.
                Both hands should be displayed.
            Player Loop:
                Player is given the option to Hit or Stay.
                If the player busts, their turn ends.
                If the player stays, their turn ends.
            Dealer Loop:
                The dealer automatically plays, with a delay between steps.
                The dealer's choices should be visible in the console.
                The dealer ends by busting or staying.
            Winner:
                The winner is the player with the highest, non-bust score.
                The winner should be displayed.
         */



        System.out.println("Game over.");
    }

    // Draws a random card from a deck with an unlimited supply.
    private static String DrawRandomCard() {
        int value = (int) (Math.random() * 13) + 1;
        switch (value) {
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
        }
        return String.valueOf(value);
    }

    // Called to show a given hand to the user, via the console.
    private static void DisplayHand(ArrayList<String> hand) {
        // TODO: Nicely output an entire hand to the console.
        // Example: Cards: 3, 7, K -- Total: 20
        for (int i = 0; i < hand.size(); i++)
        {
            System.out.println(hand.get(i) + " ");
        }
        System.out.println();

        System.out.println("Total: " + getHandTotal(hand));
    }

    // Returns the highest possible score for a given hand.
    private static int getHandTotal(ArrayList<String> hand) {
        // TODO: Calculate the total value of a hand. If there is an Ace, use the higher value unless it causes a bust.
        // Example: [A 8] should return 19. [A 6 9] should return 16.
        int handTotal = 0;

        for (int i = 0; i < hand.size(); i++){
            String card = hand.get(i);
            int cardValue = Integer.parseInt(card);
            handTotal = handTotal + cardValue;
        }
        return handTotal;
    }

    // Returns true if a value is considered a bust. Returns false if the value is still playable.
    private static boolean isValueBust(int handTotal) {

        return handTotal > 21;


    }
}

import java.util.ArrayList;
import java.util.Scanner;


public class Blackjack {
    enum Card {
        Ace(1), Two(2), Three(3), Four(4), Five(5),
        Six(6), Seven(7), Eight(8), Nine(9), Ten(10),
        Jack(10), Queen(10), King(10);

        private int value;

        Card(int value){
            this.value = value;
        }

        public static Card valueOf(int value) {
            for (Card card : values()) {
                if (card.value == value) return card;
            }
            System.out.println("No card exists for value: " + value);
            return null;
        }

        @Override
        public String toString() {
            switch (this) {
                case King:
                    return "King";
                case Queen:
                    return "Queen";
                case Jack:
                    return "Jack";
                default:
                    return "" + value;
            }
        }
    }


    private ArrayList<Card> userHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();


    public void PlayGame() throws InterruptedException {
        System.out.println("Welcome to BlackJack.");


        var randomCard = DrawRandomCard();
        userHand.add(randomCard);
        userHand.add(randomCard);

        // Show the user their hand.
        DisplayHand(userHand, "user");

        // Get a random card and give it to the dealer.
        dealerHand.add(DrawRandomCard());
        dealerHand.add(DrawRandomCard());

        // Show the user the dealer's hand.
        DisplayHand(dealerHand, "dealer");



        Scanner input = new Scanner(System.in);

        boolean userStay = false;
        boolean dealerStay = false;

        while (!userStay) {
            System.out.println("Hit or Stay?: ");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("hit")) {
                Card userNewCard = DrawRandomCard();
                userHand.add(userNewCard);
                System.out.println("You flipped a " + userNewCard);
                DisplayHand(userHand, "user");
                if (getHandTotal(userHand) > 21) {
                    System.out.println("Bust!");
                    break;
                }
            } else if (choice.equalsIgnoreCase("Stay")) {
                userStay = true;
                DisplayHand(userHand, "user");

            } else {
                System.out.println("Do not understand?");
            }
        }
        while (!dealerStay) {

            Thread.sleep(2000);

            if (getHandTotal(dealerHand) <= getHandTotal(userHand)) {
                Card dealerNewCard = DrawRandomCard();
                dealerHand.add(dealerNewCard);
                if (getHandTotal(dealerHand) > 21) {
                    System.out.println("Dealer Busts, User wins!");
                    DisplayHand(dealerHand, "dealer");
                    break;
                }

            } else {
                System.out.println("Dealer stays!");
                DisplayHand(dealerHand, "dealer");
                break;
            }
        }
        while (!dealerStay && !userStay) {

            if (getHandTotal(dealerHand) > getHandTotal(userHand)) {
                System.out.println("Dealer won!");
                break;
            }
        }

        System.out.println("Game over.");
    }

    // Draws a random card from a deck with an unlimited supply.
    private static Card DrawRandomCard() {
        int value = (int) (Math.random() * 13) + 1;
        switch(value) {
            case 13:
                return Card.King;
            case 12:
                return Card.Queen;
            case 11:
                return Card.Jack;

            default:
                return Card.valueOf(value);
        }

    }

    // Called to show a given hand to the user, via the console.
    private static void DisplayHand(ArrayList<Card> hand, String prefix) {
        System.out.print(prefix + " : ");

        for (int i = 0; i < hand.size(); i++)
        {
            System.out.println(hand.get(i) + " ");
        }
        System.out.println();

        System.out.println("Total: " + getHandTotal(hand));
    }

    // Returns the highest possible score for a given hand.
    private static int getHandTotal(ArrayList<Card> hand) {
        // TODO: Calculate the total value of a hand. If there is an Ace, use the higher value unless it causes a bust.
        // Example: [A 8] should return 19. [A 6 9] should return 16.
        int handTotal = 0;

        for (int i = 0; i < hand.size(); i++){
            Card card = hand.get(i);
            int cardValue = card.value;
            handTotal = handTotal + cardValue;
        }
        return handTotal;
    }

    // Returns true if a value is considered a bust. Returns false if the value is still playable.
    private static boolean isValueBust(int handTotal) {

        return handTotal > 21;


    }
}
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
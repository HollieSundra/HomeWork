import java.util.ArrayList;
import java.util.Scanner;



public class Blackjack {

    Scanner input = new Scanner(System.in);
    enum Card {
        Ace(1), Two(2), Three(3), Four(4), Five(5),
        Six(6), Seven(7), Eight(8), Nine(9), Ten(10),
        Jack(10), Queen(10), King(10);

        public int value;

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

    private Player userHand;
    private Player dealerHand;


    public void PlayGame() throws InterruptedException {
        System.out.println("Welcome to BlackJack.");

        userHand = new Player("user");
        dealerHand = new Player("dealer");
        userHand.wallet = 100;
        var bet = getBet();
        userHand.wallet = userHand.wallet - bet;

        var randomCard = DrawRandomCard();
        userHand.addCard(randomCard);
        userHand.addCard(randomCard);

        // Show the user their hand.
        userHand.DisplayHand();

        // Get a random card and give it to the dealer.
        dealerHand.addCard(DrawRandomCard());
        dealerHand.addCard(DrawRandomCard());

        // Show the user the dealer's hand.
        dealerHand.DisplayHand();


        boolean userStay = false;
        boolean dealerStay = false;

        while (!userStay) {
            System.out.println("Hit or Stay?: ");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("hit")) {
                Card userNewCard = DrawRandomCard();
                userHand.addCard(userNewCard);
                System.out.println("You flipped a " + userNewCard);
                userHand.DisplayHand();
                if (userHand.getHandTotal() > 21) {
                    System.out.println("Bust!");
                    break;
                }
            } else if (choice.equalsIgnoreCase("Stay")) {
                userStay = true;
                userHand.DisplayHand();

            } else {
                System.out.println("Do not understand?");
            }
        }
        while (!dealerStay) {

            Thread.sleep(2000);

            if (dealerHand.getHandTotal() <= userHand.getHandTotal()) {
                Card dealerNewCard = DrawRandomCard();
                dealerHand.addCard(dealerNewCard);
                if (dealerHand.getHandTotal() > 21) {
                    System.out.println("Dealer Busts, User wins!");
                    dealerHand.DisplayHand();
                    userHand.wallet = userHand.wallet + (bet * 2);
                    System.out.println("You won: $" + (bet * 2));
                    System.out.println("Your wallet total: $" + userHand.wallet);
                    break;
                }

            } else {
                System.out.println("Dealer stays!");
                dealerHand.DisplayHand();
                break;
            }
        }
        while (!dealerStay && !userStay) {

            if (dealerHand.getHandTotal() > userHand.getHandTotal()) {
                System.out.println("Dealer won!");
                break;
            }
        }

        System.out.println("Game over.");
    }

    public int getBet() {

        System.out.println("Place your bet");
        String choice = input.nextLine();
        int bet;
        bet = Integer.valueOf(choice);
        return bet;

    }

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
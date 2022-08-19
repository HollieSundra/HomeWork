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

    private enum GameState {
        Playing,
        PlayerBust,
        PlayerStay,
        DealerBust,
        DealerWon,
        Draw
    }

    private User userHand;
    private Dealer dealerHand;

    private User wallet;


    private GameState gameState;

    private IDeck deck;


    public void PlayGame() throws InterruptedException {
        System.out.println("Welcome to BlackJack.");

        deck = new InfiniteDeck();

        userHand = new User("user");
        dealerHand = new Dealer("dealer");
        userHand.setFunds(100);
        var bet = getBet();
        userHand.subtractFunds(bet);

        var randomCard = deck.DrawRandomCard();
        userHand.addCard(randomCard);
        userHand.addCard(randomCard);

        // Show the user their hand.
        userHand.DisplayHand();

        // Get a random card and give it to the dealer.
        dealerHand.addCard(deck.DrawRandomCard());
        dealerHand.addCard(deck.DrawRandomCard());

        // Show the user the dealer's hand.
       // dealerHand.DisplayHand();


        boolean userStay = false;
        boolean dealerStay = false;

        gameState = GameState.Playing;

        // Player's Turn
        while (gameState == GameState.Playing) {
            System.out.println("Hit or Stay?: ");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("hit")) {
                Card userNewCard = deck.DrawRandomCard();
                userHand.addCard(userNewCard);
                System.out.println("You flipped a " + userNewCard);
                userHand.DisplayHand();
                if (userHand.getHandTotal() > 21) {
                    gameState = GameState.PlayerBust;
                    dealerHand.DisplayHand();
                    break;
                }
            } else if (choice.equalsIgnoreCase("Stay")) {
                gameState = GameState.PlayerStay;
                userHand.DisplayHand();

            } else {
                System.out.println("Do not understand?");
            }
        }

        // Dealer's Turn
        while (gameState == GameState.PlayerStay) {
            Thread.sleep(2000);

            var dealerTotal = dealerHand.getHandTotal();

                dealerHand.DisplayHand();

            if (dealerTotal > 21) {
                gameState = GameState.DealerBust;
            } else if (dealerTotal < userHand.getHandTotal()) {
                Card dealerNewCard = deck.DrawRandomCard();
                dealerHand.addCard(dealerNewCard);
            } else if (dealerTotal == userHand.getHandTotal()) {
                gameState = GameState.Draw;
            } else {
                gameState = GameState.DealerWon;
            }
        }

        switch (gameState) {
            case PlayerBust -> {
                // Tell the player they bust!
                System.out.println("You Bust!");
                userHand.subtractFunds(bet);
                System.out.println("You lost: $" + bet);
                System.out.println("Your wallet total: $" + userHand.totalFunds());
            }
            case DealerBust -> {
                // Tell the user they won!
                userHand.addFunds(bet);
                System.out.println("You won: $" + bet);
                System.out.println("Your wallet total: $" + userHand.totalFunds());
            }
            case DealerWon -> {
                System.out.println("Dealer won!");
                userHand.subtractFunds(bet);
                System.out.println("You lost: $" + bet);
                System.out.println("Your wallet total: $" + userHand.totalFunds());
            }
            case Draw -> {
                System.out.println("Draw!");
                userHand.addFunds(bet);
                System.out.println("Your wallet total: $" + userHand.totalFunds());
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



    // Returns true if a value is considered a bust. Returns false if the value is still playable.
    private static boolean isValueBust(int handTotal) {

        return handTotal > 21;


    }
}

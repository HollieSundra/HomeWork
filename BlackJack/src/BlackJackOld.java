import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    private static ArrayList<Integer> userHand = new ArrayList<>();
    private static ArrayList<Integer> dealerHand = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        // Build initial hand (2 cards)
        userHand.add(getCard());
        userHand.add(getCard());
        // dealers hand
        dealerHand.add(getCard());
        dealerHand.add(getCard());

        // Display hand to user
        displayUserHand();

        userTotal();
        System.out.println(userTotal());



        Scanner input = new Scanner(System.in);


        // Game Loop
        boolean userStay = false;
        boolean dealerStay = false;
        // Dealers loop

        while (!userStay) {
            // Prompt user to hit or stay
            System.out.println("Hit or Stay?: ");
            String choice = input.nextLine();

            // Honor their choice!
            if (choice.equalsIgnoreCase("hit"))
            {
                int userNewCard = getCard();
                userHand.add(userNewCard);
                System.out.println("You flipped a " + userNewCard);
                displayUserHand();
                if (userTotal() > 21) {
                    System.out.println("Bust!");
                    break;
                }

            }
            else if (choice.equalsIgnoreCase("stay")) {
                userStay = true;
                displayUserHand();

            }
            else {
                System.out.println("Do not understand?");
            }
        }

        System.out.println("Dealer's turn!");

        while (!dealerStay) {

            Thread.sleep(2000);

            if (dealerTotal() <= userTotal()) {
                int dealerNewCard = getCard();
                dealerHand.add(dealerNewCard);
                displayDealerHand();
                System.out.println("Dealer flipped a " + dealerNewCard);
                if (dealerTotal() > 21) {
                    System.out.println("Dealer Busts, User wins!");
                    displayDealerHand();
                    break;

                }
            }
            else {
                System.out.println("Dealer stays!");
                displayDealerHand();
                break;
            }



        }

        while (!dealerStay && !userStay) {

            if (dealerTotal() > userTotal()) {
                System.out.println("Dealer won!");
                break;
            }

                else {
                    System.out.println("User won!");
                    break;
                }
        }
    }


    public static int getCard() {
        int value = (int) (Math.random() * 14) + 1;
        return value;

    }

    public static void displayUserHand()
    {
        for (int i = 0; i < userHand.size(); i++)
        {
            System.out.print(userHand.get(i) + " ");
        }
        System.out.println();

        System.out.println("Total: " + userTotal());
    }

    public static void displayDealerHand()
    {
        for (int i = 0; i < dealerHand.size(); i++)
        {
            System.out.println(dealerHand.get(i) + " ");
        }
        System.out.println();

        System.out.println("Total: " + dealerTotal());

    }
    public static int userTotal() {
        int tepTotal = 0;

        for (int i = 0; i < userHand.size(); i++){
            tepTotal += userHand.get(i);
        }

        return tepTotal;
    }
    public static int dealerTotal() {
        int posTotal = 0;

        for (int i = 0; i < dealerHand.size(); i ++){
            posTotal += dealerHand.get(i);
        }
        return posTotal;
    }


    public static int faceCard(int cardNumber) {

        switch (cardNumber) {
            case 11:
                System.out.println("J");
                break;

            case 12:
                System.out.println("Q");
                break;

            case 13:
                System.out.println("K");
                break;

            case 1:
                System.out.println("A");
        }
        return cardNumber;

    }
}
// getDisplayValue
// getScoreValue
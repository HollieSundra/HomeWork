public interface IDeck {
    int deck = 52;
    String spade = "Spade";
    String heart = "Heart";
    String club = "Club";
    String diamond = "diamond";

    // Deals a card from the deck
    public Blackjack.Card DrawRandomCard();

    // Returns a card formerly dealt to be eventually shuffled back in.
    public void TrashCard(Blackjack.Card card);

}

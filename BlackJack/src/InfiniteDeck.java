public class InfiniteDeck implements IDeck {

    @Override
    public Blackjack.Card DrawRandomCard() {
        int value = (int) (Math.random() * 13) + 1;
        switch(value) {
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

    @Override
    public void TrashCard(Blackjack.Card card) {

    }
}

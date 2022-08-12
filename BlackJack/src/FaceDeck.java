public class FaceDeck implements IDeck {

    @Override
    public Blackjack.Card DrawRandomCard() {
        int value = (int) (Math.random() * 3);
        switch(value) {
            case 0:
                return Blackjack.Card.King;
            case 1:
                return Blackjack.Card.Queen;
            case 2:
                return Blackjack.Card.Jack;
        }

        // Shouldn't happen.
        return null;
    }

    @Override
    public void TrashCard(Blackjack.Card card) {

    }
}

package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.util.Constants;

public class Dealer extends Gamer {

    private final Deck deck;

    public Dealer(final Deck deck) {
        super();
        this.deck = deck;

        selfDraw();
        selfDraw();
    }

    public Card draw() {
        return deck.drawn();
    }

    private void selfDraw() {
        Card card = this.deck.drawn();
        hand.add(card);
    }

    public Card showFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isSameScore(final long score) {
        return hand.calculateScore() == score;
    }

    @Override
    boolean canReceiveCard() {
        return hand.calculateScore() <= Constants.DEALER_BOUND;
    }

    public boolean extraCard() {
        if (canReceiveCard()) {
            selfDraw();
            return true;
        }
        return false;
    }
}

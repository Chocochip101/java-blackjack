package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Hand {

    public static final int BLACKJACK_BOUND = 21;
    public static final int BLACKJACK_CARD_SIZE = 2;
    private static final int MAX_ACE_SCORE = 11;

    private final Queue<Card> cards;

    public Hand() {
        this.cards = new LinkedList<>();
    }

    public void add(final Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public long calculateScore() {
        long aceCount = countAce();
        int scoreWithoutAce = calculateScoreWithoutAce();

        int residualScore = BLACKJACK_BOUND - scoreWithoutAce;

        int quotient = residualScore / MAX_ACE_SCORE;
        long minAceCount = Math.min(quotient, aceCount);

        return calculate(aceCount, scoreWithoutAce, minAceCount);
    }

    private long countAce() {
        return cards.stream()
                .filter(card -> (card.getRank() == Rank.ACE))
                .count();
    }

    private int calculateScoreWithoutAce() {
        return cards.stream()
                .filter(card -> (card.getRank() != Rank.ACE))
                .mapToInt(c -> c.getScore().get(0))
                .sum();
    }

    private long calculate(final long aceCount, final int scoreWithoutAce, final long minAceCount) {
        if (aceCount > 0) {
            return scoreWithoutAce + minAceCount * MAX_ACE_SCORE + (aceCount - minAceCount);
        }

        return scoreWithoutAce;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_BOUND && cards.size() == BLACKJACK_CARD_SIZE;
    }

    public Card getFirstCard() {
        return cards.peek();
    }

    public List<Card> getCards() {
        return cards.stream()
                .toList();
    }
}

package blackjack.dto;

import blackjack.domain.ResultStatus;

public class DealerResult {
    private final int wins;
    private final int loses;
    private final int draws;

    public DealerResult(final int wins, final int loses, final int draws) {
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public static DealerResult of(final ResultStatus resultStatus) {
        return new DealerResult(resultStatus.getWins(), resultStatus.getLoses(), resultStatus.getDraws());
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getDraws() {
        return draws;
    }
}

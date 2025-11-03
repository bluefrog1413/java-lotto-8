package lotto.model;

public enum Result {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000),
    MISS(0, 0);

    private final int matchCount;
    private final int reward;

    Result(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

    public static Result of(int matchCount, boolean hasBonus) {
        if (matchCount == 6) return SIX;
        if (matchCount == 5 && hasBonus) return FIVE_BONUS;
        if (matchCount == 5) return FIVE;
        if (matchCount == 4) return FOUR;
        if (matchCount == 3) return THREE;
        return MISS;
    }

}

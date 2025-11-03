package lotto.model;

import java.util.EnumMap;
import java.util.Map;

public class ResultStatistics {
    private final Map<Result, Integer> results = new EnumMap<>(Result.class);
    private int totalPrice = 0;

    public ResultStatistics() {
        for (Result result : Result.values()) {
            results.put(result, 0);
        }
    }


    public void add(Result result) {
        results.put(result, results.get(result) + 1);
        totalPrice += result.getReward();
    }

    public int getCount(Result result) {
        return results.get(result);
    }

    public void print() {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (5,000원) - %d개%n", results.get(Result.THREE));
        System.out.printf("4개 일치 (50,000원) - %d개%n", results.get(Result.FOUR));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", results.get(Result.FIVE));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", results.get(Result.FIVE_BONUS));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", results.get(Result.SIX));
    }

    public int getTotalPrice(){
        return totalPrice;
    }
}

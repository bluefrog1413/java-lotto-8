package lotto.view;

import lotto.model.Result;
import lotto.model.ResultStatistics;

public class OutputView {
    public void lottoCount(int count){
        System.out.println(count + "개를 구매했습니다.");
    }

    public void showResult(ResultStatistics statistics) {
        System.out.println("당첨 통계");
        System.out.println("---");

        System.out.printf("3개 일치 (5,000원) - %d개%n", statistics.getCount(Result.THREE));
        System.out.printf("4개 일치 (50,000원) - %d개%n", statistics.getCount(Result.FOUR));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", statistics.getCount(Result.FIVE));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", statistics.getCount(Result.FIVE_BONUS));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", statistics.getCount(Result.SIX));
    }

    public void showProfitRate(double profitRate){
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}

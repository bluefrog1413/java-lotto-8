package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultStatisticsTest {

    @Test
    @DisplayName("생성 시 모든 결과의 개수는 0이다")
    void init_allZero() {
        ResultStatistics stats = new ResultStatistics();

        assertThat(stats.getCount(Result.THREE)).isZero();
        assertThat(stats.getCount(Result.FOUR)).isZero();
        assertThat(stats.getCount(Result.FIVE)).isZero();
        assertThat(stats.getCount(Result.FIVE_BONUS)).isZero();
        assertThat(stats.getCount(Result.SIX)).isZero();
        assertThat(stats.getCount(Result.MISS)).isZero(); // enum에 있으므로 초기화돼야 함
        assertThat(stats.getTotalPrice()).isZero();
    }

    @Test
    @DisplayName("당첨 결과를 추가하면 해당 결과의 개수가 1 증가하고 총 당첨 금액이 누적된다")
    void add_increaseCountAndTotalPrice() {
        ResultStatistics stats = new ResultStatistics();

        stats.add(Result.THREE);  // 5,000
        stats.add(Result.FOUR);   // 50,000

        assertThat(stats.getCount(Result.THREE)).isEqualTo(1);
        assertThat(stats.getCount(Result.FOUR)).isEqualTo(1);
        assertThat(stats.getTotalPrice()).isEqualTo(5000 + 50000);
    }

    @Test
    @DisplayName("MISS를 추가하면 개수는 증가하지만 총 당첨 금액은 증가하지 않는다")
    void add_miss_notIncreaseTotalPrice() {
        ResultStatistics stats = new ResultStatistics();

        stats.add(Result.MISS);

        assertThat(stats.getCount(Result.MISS)).isEqualTo(1);
        assertThat(stats.getTotalPrice()).isZero();
    }

    @Test
    @DisplayName("여러 결과를 추가하면 각 결과가 올바르게 집계된다")
    void add_multipleResults() {
        ResultStatistics stats = new ResultStatistics();

        stats.add(Result.THREE);       // 5,000
        stats.add(Result.SIX);         // 2,000,000,000
        stats.add(Result.FIVE_BONUS);  // 30,000,000원
        stats.add(Result.MISS);        // 0원

        assertThat(stats.getCount(Result.THREE)).isEqualTo(1);
        assertThat(stats.getCount(Result.SIX)).isEqualTo(1);
        assertThat(stats.getCount(Result.FIVE_BONUS)).isEqualTo(1);
        assertThat(stats.getCount(Result.MISS)).isEqualTo(1);

        // 2,000,000,000 + 30,000,000 + 5,000 = 2,030,005,000
        assertThat(stats.getTotalPrice()).isEqualTo(2_030_005_000);
    }
}

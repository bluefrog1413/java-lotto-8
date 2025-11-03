package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    @DisplayName("각 enum이 가진 일치 개수와 당첨금이 올바르게 설정되어 있다")
    void enumFields() {
        assertThat(Result.THREE.getMatchCount()).isEqualTo(3);
        assertThat(Result.THREE.getReward()).isEqualTo(5_000);

        assertThat(Result.FOUR.getMatchCount()).isEqualTo(4);
        assertThat(Result.FOUR.getReward()).isEqualTo(50_000);

        assertThat(Result.FIVE.getMatchCount()).isEqualTo(5);
        assertThat(Result.FIVE.getReward()).isEqualTo(1_500_000);

        assertThat(Result.FIVE_BONUS.getMatchCount()).isEqualTo(5);
        assertThat(Result.FIVE_BONUS.getReward()).isEqualTo(30_000_000);

        assertThat(Result.SIX.getMatchCount()).isEqualTo(6);
        assertThat(Result.SIX.getReward()).isEqualTo(2_000_000_000);

        assertThat(Result.MISS.getMatchCount()).isZero();
        assertThat(Result.MISS.getReward()).isZero();
    }

    @Test
    @DisplayName("당첨 개수만으로 등수를 판별한다 (보너스 없음)")
    void of_withoutBonus() {
        assertThat(Result.of(6, false)).isEqualTo(Result.SIX);
        assertThat(Result.of(5, false)).isEqualTo(Result.FIVE);
        assertThat(Result.of(4, false)).isEqualTo(Result.FOUR);
        assertThat(Result.of(3, false)).isEqualTo(Result.THREE);
        assertThat(Result.of(2, false)).isEqualTo(Result.MISS);
        assertThat(Result.of(0, false)).isEqualTo(Result.MISS);
    }

    @Test
    @DisplayName("5개 일치일 때만 보너스 여부로 FIVE/FIVE_BONUS가 갈린다")
    void of_withBonus() {
        // 5개 + 보너스 일치
        assertThat(Result.of(5, true)).isEqualTo(Result.FIVE_BONUS);

        // 6개일 때는 보너스 여부와 관계없이 SIX
        assertThat(Result.of(6, true)).isEqualTo(Result.SIX);

        // 4개 이하는 보너스 있어도 등수는 그대로
        assertThat(Result.of(4, true)).isEqualTo(Result.FOUR);
        assertThat(Result.of(3, true)).isEqualTo(Result.THREE);
        assertThat(Result.of(2, true)).isEqualTo(Result.MISS);
    }
}

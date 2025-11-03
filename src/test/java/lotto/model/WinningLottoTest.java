package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 유효하면 객체가 생성된다")
    void createWinningLotto_success() {
        WinningLotto winningLotto = new WinningLotto(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                7
        );

        assertThat(winningLotto.getLotto().getNumber())
                .containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외를 발생시킨다")
    void createWinningLotto_duplicateBonus_throwsException() {
        assertThatThrownBy(() ->
                new WinningLotto(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        6   // 당첨 번호에 포함된 숫자
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("보너스 번호가 1보다 작으면 예외를 발생시킨다")
    void createWinningLotto_bonusTooSmall_throwsException() {
        assertThatThrownBy(() ->
                new WinningLotto(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        0
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 45보다 크면 예외를 발생시킨다")
    void createWinningLotto_bonusTooLarge_throwsException() {
        assertThatThrownBy(() ->
                new WinningLotto(
                        Arrays.asList(1, 2, 3, 4, 5, 6),
                        46
                )
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 1~45 사이여야 합니다.");
    }
}

package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    @DisplayName("처음 생성된 Lottos는 비어 있어야 한다")
    void newLottos_isEmpty() {
        Lottos lottos = new Lottos();

        assertThat(lottos.getLottos()).isEmpty();
        assertThat(lottos.getPrice()).isZero();
    }

    @Test
    @DisplayName("로또 한 장을 추가하면 리스트에 담긴다")
    void addLotto_addsOne() {
        Lottos lottos = new Lottos();
        List<Integer> lotto = Arrays.asList(1, 2, 3, 4, 5, 6);

        lottos.addLotto(lotto);

        assertThat(lottos.getLottos())
                .hasSize(1)
                .containsExactly(lotto);
    }

    @Test
    @DisplayName("로또 여러 장을 추가하면 순서대로 담긴다")
    void addLotto_addsMultiple() {
        Lottos lottos = new Lottos();
        List<Integer> lotto1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lotto2 = Arrays.asList(7, 8, 9, 10, 11, 12);

        lottos.addLotto(lotto1);
        lottos.addLotto(lotto2);

        assertThat(lottos.getLottos())
                .hasSize(2)
                .containsExactly(lotto1, lotto2);
    }

    @Test
    @DisplayName("구입 금액을 저장하고 다시 꺼낼 수 있다")
    void addPrice_andGet() {
        Lottos lottos = new Lottos();

        lottos.addPrice(5000);

        assertThat(lottos.getPrice()).isEqualTo(5000);
    }
}

package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Result;
import lotto.model.ResultStatistics;
import lotto.model.WinningLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoService {
    private final ResultStatistics resultStatistics = new ResultStatistics();

    public int calculateLottoCount(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1000원 단위만 가능합니다.");
        }
        return amount / 1000;
    }

    public List<Integer> createLotto() {
        List<Integer> lotto = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(lotto);
        System.out.println(lotto);
        return lotto;
    }

    // ✅ WinningLotto를 직접 받아서 비교하도록 변경
    public void compare(List<List<Integer>> purchasedLottos, WinningLotto winningLotto) {
        for (List<Integer> lotto : purchasedLottos) {
            compareNumber(lotto, winningLotto);
        }
    }

    // ✅ 개별 로또를 당첨 객체와 비교
    private void compareNumber(List<Integer> lotto, WinningLotto winningLotto) {
        Lotto winning = winningLotto.getLotto();
        List<Integer> winningNumbers = winning.getNumber();
        int bonusNumber = winningLotto.getBonusNumber();

        int matchCount = 0;
        for (Integer num : lotto) {
            if (winningNumbers.contains(num)) {
                matchCount++;
            }
        }

        boolean bonus = (matchCount == 5) && lotto.contains(bonusNumber);

        Result result = Result.of(matchCount, bonus);
        resultStatistics.add(result);
    }

    public ResultStatistics getResultStatistics() {
        return resultStatistics;
    }

    public double calculateProfitRate(Lottos lottos) {
        int purchaseAmount = lottos.getPrice();
        int totalWinning = resultStatistics.getTotalPrice();

        if (totalWinning == 0) {
            return 0;
        }
        return (double) totalWinning / purchaseAmount * 100;
    }
}

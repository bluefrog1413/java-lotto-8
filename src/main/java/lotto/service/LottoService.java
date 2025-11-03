package lotto.service;

import java.util.Collections;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.Lottos;
import lotto.model.Result;
import lotto.model.ResultStatistics;

public class LottoService {
    private final ResultStatistics resultStatistics = new ResultStatistics();

    public int calculateLottoCount(int amount){
        if(amount % 1000 != 0) throw new IllegalArgumentException("[ERROR] 구매 금액은 1000원 단위만 가능합니다.");
        return amount / 1000;
    }

    public List<Integer> createLotto(){
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(lotto);
        System.out.println(lotto);
        return lotto;
    }

    public void compare(List<List<Integer>> lottos, List<Integer> WinningNumber, Integer bonusNumber){
        for(List<Integer> lotto : lottos){
            compareNumber(lotto,WinningNumber,bonusNumber);
        }
    }

    public void compareNumber(List<Integer> lotto, List<Integer> WinningNumber, Integer bounusNumber){
        int matchCount = 0;
        boolean bonus = false;
        for (Integer lottoNumber : lotto) {
            if (eachCompareNumber(lottoNumber, WinningNumber)) matchCount++;
        }
        if(matchCount == 5) {
            if(compareBonusNumber(lotto,bounusNumber)) bonus = true;
        }

        Result result = Result.of(matchCount, bonus);
        resultStatistics.add(result);
    }

    public boolean eachCompareNumber(Integer number, List<Integer> WinningNumber){
        for (Integer winningNumber : WinningNumber) {
            if (number.equals(winningNumber)) return true;
        }
        return false;
    }

    public boolean compareBonusNumber(List<Integer> lotto, Integer bonusNumber){
        for (Integer lottoNumber : lotto) {
            if (lottoNumber.equals(bonusNumber)) return true;
        }
        return false;
    }

    public ResultStatistics getResultStatistics() {
        return resultStatistics;
    }

    public double calculateProfitRate(Lottos lottos){
        int purchaseAmount = lottos.getPrice();
        int totalPrice = resultStatistics.getTotalPrice();

        if(totalPrice == 0) return 0;
        return (double) totalPrice / purchaseAmount * 100;
    }
}

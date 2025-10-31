package lotto.service;

import java.util.Collections;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoService {
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
}

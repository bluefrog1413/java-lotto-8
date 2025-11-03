package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public int purchaseAmount(){
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(Console.readLine());
    }

    public List<Integer> WinningNumber(){
        System.out.println("당첨 번호를 입력해 주세요");
        String input = Console.readLine();
        String[] tokens = input.split(",");
        List<Integer> lottoNumbers = new ArrayList<>();
        for(String token : tokens){
            lottoNumbers.add(Integer.parseInt(token));
        }
        return lottoNumbers;
    }

    public int inputBonusNumber(){
        System.out.println("보너스 번호를 입력해 주세요.");
        return Integer.parseInt(Console.readLine());
        }
}

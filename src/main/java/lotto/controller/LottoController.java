package lotto.controller;

import lotto.model.Lottos;
import lotto.model.ResultStatistics;
import lotto.model.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;
    private final Lottos lottos;
    private final ResultStatistics resultStatistics;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView,
                           Lottos lottos, ResultStatistics resultStatistics) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottos = lottos;
        this.resultStatistics = resultStatistics;
    }

    public void run() {
        // 1. 구매 금액 입력 및 로또 생성
        int price = inputPurchaseAmount();
        lottos.addPrice(price);
        int count = lottoService.calculateLottoCount(price);
        outputView.lottoCount(count);

        for (int i = 0; i < count; i++) {
            lottos.addLotto(lottoService.createLotto());
        }

        // 2. 당첨 번호 & 보너스 번호 입력
        WinningLotto winningLotto = inputWinningLotto();

        // 3. 비교 로직 수행
        lottoService.compare(lottos.getLottos(), winningLotto);

        // 4. 결과 출력
        outputView.showResult(lottoService.getResultStatistics());
        outputView.showProfitRate(lottoService.calculateProfitRate(lottos));
    }

    private int inputPurchaseAmount() {
        while (true) {
            try {
                int price = inputView.purchaseAmount();
                lottoService.calculateLottoCount(price); // 유효한 금액인지 검증
                return price;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto inputWinningLotto() {
        while (true) {
            try {
                List<Integer> numbers = inputView.WinningNumber();
                int bonus = inputView.inputBonusNumber();
                return new WinningLotto(numbers, bonus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

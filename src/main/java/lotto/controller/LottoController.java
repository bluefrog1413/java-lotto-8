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
        int price = inputPurchaseAmount();
        lottos.addPrice(price);
        int count = lottoService.calculateLottoCount(price);
        outputView.lottoCount(count);
        for (int i = 0; i < count; i++) lottos.addLotto(lottoService.createLotto());
        WinningLotto winningLotto = inputWinningLotto();
        lottoService.compare(lottos.getLottos(), winningLotto);
        outputView.showResult(lottoService.getResultStatistics());
        outputView.showProfitRate(lottoService.calculateProfitRate(lottos));
    }

    private int inputPurchaseAmount() {
        while (true) {
            try {
                int price = inputView.purchaseAmount();
                lottoService.calculateLottoCount(price);
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

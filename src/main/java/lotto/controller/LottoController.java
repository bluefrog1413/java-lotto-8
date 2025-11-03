package lotto.controller;

import lotto.Lotto;
import lotto.model.Lottos;
import lotto.model.ResultStatistics;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;
    private final Lottos lottos;
    private Lotto lotto;
    private final ResultStatistics resultStatistics;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView, Lottos lottos, ResultStatistics resultStatistics){
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottos = lottos;
        this.resultStatistics = resultStatistics;
    }

    public void run(){
        int price = inputView.purchaseAmount();
        lottos.addPrice(price);
        int count = lottoService.calculateLottoCount(price);
        outputView.lottoCount(count);
        for (int i = 0; i < count; i++) lottos.addLotto(lottoService.createLotto());
        Lotto lotto = new Lotto(inputView.WinningNumber(), inputView.inputBonusNumber());
        lottoService.compare(lottos.getLottos(), lotto.getNumber(), lotto.getBonusNumber());
        outputView.showResult(lottoService.getResultStatistics());
        outputView.showProfitRate(lottoService.calculateProfitRate(lottos));
    }
}

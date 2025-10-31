package lotto.controller;

import lotto.Lotto;
import lotto.model.Lottos;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;
    private final Lottos lottos;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView, Lottos lottos){
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottos = lottos;
    }

    public void run(){
        int count = lottoService.calculateLottoCount(inputView.CalculateLottoCount());
        outputView.lottoCount(count);
        for (int i = 0; i < count; i++) lottos.addLotto(lottoService.createLotto());

    }
}

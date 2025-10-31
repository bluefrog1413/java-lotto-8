package lotto;
import lotto.controller.LottoController;
import lotto.model.Lottos;
import lotto.service.LottoService;
import lotto.view.*;

public class Application {
    public static void main(String[] args) {

        LottoService lottoService = new LottoService();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Lottos lottos = new Lottos();

        LottoController lottoController = new LottoController(lottoService, inputView, outputView, lottos);

        lottoController.run();
    }
}

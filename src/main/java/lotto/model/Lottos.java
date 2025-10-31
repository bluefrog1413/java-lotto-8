package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<List<Integer>> lottos = new ArrayList<>();

    public void addLotto(List<Integer> lotto){
        lottos.add(lotto);
    }

    public List<List<Integer>> getLottos(){
        return lottos;
    }
}

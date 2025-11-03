package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<List<Integer>> lottos = new ArrayList<>();
    private int price;

    public void addLotto(List<Integer> lotto){
        lottos.add(lotto);
    }

    public void addPrice(int price){
        this.price = price;
    }

    public List<List<Integer>> getLottos(){
        return lottos;
    }
}

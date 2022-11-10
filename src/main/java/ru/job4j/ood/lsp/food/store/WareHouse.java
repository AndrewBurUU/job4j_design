package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

public class WareHouse extends AbstractStore {

    private static final int FRESHNESS = 25;

    public WareHouse(String name) {
        super(name);
    }

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (isExpired(food)) {
            super.setFoods(food);
            res = true;
        }
        return res;
    }

    @Override
    public boolean isExpired(Food food) {
        return super.getPercent(food) < FRESHNESS;
    }

}

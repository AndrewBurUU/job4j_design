package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

public class Trash extends AbstractStore {

    private static final int FRESHNESS = 100;

    public Trash(String name) {
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
        return super.getPercent(food) >= FRESHNESS;
    }

}

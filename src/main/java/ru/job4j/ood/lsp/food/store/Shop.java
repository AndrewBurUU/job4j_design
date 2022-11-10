package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

public class Shop extends AbstractStore {

    private static final int FRESHNESS25 = 25;
    private static final int FRESHNESS75 = 75;
    private static final int FRESHNESS100 = 100;

    public Shop(String name) {
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
        boolean res = false;
        double freshness = super.getPercent(food);
        if (freshness > FRESHNESS25 && freshness < FRESHNESS100) {
            if (freshness > FRESHNESS75) {
                food.setPrice(food.getPrice() - food.getDiscount());
            }
            res = true;
        }
        return res;
    }

}

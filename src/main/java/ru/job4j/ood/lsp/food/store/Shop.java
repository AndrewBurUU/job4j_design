package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class Shop extends AbstractStore {

    public static final int FRESHNESS_75 = 75;
    public ExpirationCalculator<LocalDate> expirationCalculator;

    public Shop(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    public boolean isExpired(Food food) {
        boolean res = false;
        double freshness = expirationCalculator.getPercent(food);
        if (freshness > WareHouse.FRESHNESS_25 && freshness < Trash.FRESHNESS_100) {
            if (freshness > FRESHNESS_75) {
                food.setPrice(food.getPrice() - food.getDiscount());
            }
            res = true;
        }
        return res;
    }

}

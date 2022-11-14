package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class WareHouse extends AbstractStore {

    public static final int FRESHNESS_25 = 25;
    public ExpirationCalculator<LocalDate> expirationCalculator;

    public WareHouse(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    public boolean isExpired(Food food) {
        return expirationCalculator.getPercent(food) < FRESHNESS_25;
    }

}

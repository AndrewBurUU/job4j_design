package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class Trash extends AbstractStore {

    public static final int FRESHNESS_100 = 100;

    public Trash(ExpirationCalculator<LocalDate> expirationCalculator) {
        super(expirationCalculator);
    }

    @Override
    public boolean isExpired(Food food) {
        return super.expirationCalculator.getPercent(food) >= FRESHNESS_100;
    }

}

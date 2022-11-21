package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public interface ExpirationCalculator<T> {

    double getPercent(Food food);

    LocalDate getLocalDate();
}

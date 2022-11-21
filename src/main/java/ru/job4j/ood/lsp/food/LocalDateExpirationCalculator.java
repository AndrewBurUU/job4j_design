package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;
import java.time.temporal.*;

public class LocalDateExpirationCalculator implements ExpirationCalculator<LocalDate> {

    private LocalDate localDate;

    public LocalDateExpirationCalculator(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public double getPercent(Food food) {
        LocalDate localDate = LocalDate.now();
        long periodExpired = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long periodOnDate = ChronoUnit.DAYS.between(food.getCreateDate(), localDate);
        return periodOnDate * 100 / periodExpired;
    }

    @Override
    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}

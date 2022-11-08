package ru.job4j.ood.lsp.food;

import java.time.*;

public class Trash extends AbstractStore {

    public Trash(String name) {
        super(name);
    }

    @Override
    public Food add(Food food, LocalDate localDate) {
        Food res = null;
        super.add(food, localDate);
        if (super.getPeriodOnDate() >= super.getPeriodExpired()) {
            super.setFoods(food);
            res = food;
        }
        return res;
    }

}

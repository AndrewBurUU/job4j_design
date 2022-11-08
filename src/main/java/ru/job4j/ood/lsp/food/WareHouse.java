package ru.job4j.ood.lsp.food;

import java.time.*;

public class WareHouse extends AbstractStore {

    public WareHouse(String name) {
        super(name);
    }

    @Override
    public Food add(Food food, LocalDate localDate) {
        Food res = null;
        super.add(food, localDate);
        if (super.getPeriodOnDate() < super.getFreshNess25()) {
            super.setFoods(food);
            res = food;
        }
        return res;
    }

}

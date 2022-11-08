package ru.job4j.ood.lsp.food;

import java.time.*;

public class Shop extends AbstractStore {

    public Shop(String name) {
        super(name);
    }

    @Override
    public Food add(Food food, LocalDate localDate) {
        Food res = null;
        super.add(food, localDate);
        if (super.getPeriodOnDate() > super.getFreshNess25()
                && super.getPeriodOnDate() < super.getPeriodExpired()) {
            if (super.getPeriodOnDate() > super.getFreshNess75()) {
                food.setPrice(food.getPrice() - food.getDiscount());
            }
            super.setFoods(food);
            res = food;
        }
        return res;
    }

}

package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class Shop extends AbstractStore {

    public Shop(String name) {
        super(name);
    }

    @Override
    public boolean add(Food food) {
        boolean res = false;
        super.add(food);
        if (super.getPeriodOnDate() > super.getFreshNess25()
                && super.getPeriodOnDate() < super.getPeriodExpired()) {
            if (super.getPeriodOnDate() > super.getFreshNess75()) {
                food.setPrice(food.getPrice() - food.getDiscount());
            }
            super.setFoods(food);
            res = true;
        }
        return res;
    }

    @Override
    public boolean isExpired(Food food) {
        return true;
    }

}

package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class Trash extends AbstractStore {

    public Trash(String name) {
        super(name);
    }

    @Override
    public boolean add(Food food) {
        boolean res = false;
        super.add(food);
        if (super.getPeriodOnDate() >= super.getPeriodExpired()) {
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

package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;
import java.util.*;

public abstract class AbstractStore implements Store {

    private final List<Food> foods = new ArrayList<>();

    protected abstract boolean isExpired(Food food);

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (isExpired(food)) {
            foods.add(food);
            res = true;
        }
        return res;
    }

    @Override
    public void clear() {
        foods.clear();
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractStore that = (AbstractStore) o;
        return Objects.equals(foods, that.foods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foods);
    }

}

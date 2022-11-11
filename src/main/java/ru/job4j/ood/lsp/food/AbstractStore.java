package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;
import java.util.*;

public abstract class AbstractStore implements Store {

    private final List<Food> foods = new ArrayList<>();
    public ExpirationCalculator<LocalDate> expirationCalculator;

    public AbstractStore(ExpirationCalculator<LocalDate> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    protected abstract boolean isExpired(Food food);

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (isExpired(food)) {
            setFoods(food);
            res = true;
        }
        return res;
    }

    @Override
    public List<Food> getAll() {
        List<Food> res = new ArrayList<>();
        res = foods;
        return res;
    }

    public void setFoods(Food food) {
        this.foods.add(food);
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

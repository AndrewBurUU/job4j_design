package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;
import java.util.*;
import java.time.temporal.*;

public abstract class AbstractStore implements Store {

    private final List<Food> foods = new ArrayList<>(100);
    private int ids = 1;
    private String name;
    private long periodExpired;
    private long periodOnDate;

    public AbstractStore(String name) {
        this.name = name;
    }

    protected boolean isExpired(Food food) {
        return false;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < foods.size(); index++) {
            if (foods.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean add(Food food) {
        return isExpired(food);
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }

    public double getPercent(Food food) {
        LocalDate localDate = LocalDate.now();
        this.periodExpired = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        this.periodOnDate = ChronoUnit.DAYS.between(food.getCreateDate(), localDate);
        return periodOnDate * 100 / periodExpired;
    }

    public void setFoods(Food food) {
        food.setId(ids++);
        this.foods.add(food);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPeriodExpired() {
        return periodExpired;
    }

    public long getPeriodOnDate() {
        return periodOnDate;
    }

}

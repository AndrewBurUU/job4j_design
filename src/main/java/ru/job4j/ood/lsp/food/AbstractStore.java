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
    private long freshNess25;
    private long freshNess75;
    private LocalDate localDate;

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
        this.periodExpired = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        this.periodOnDate = ChronoUnit.DAYS.between(food.getCreateDate(), localDate);
//        this.freshNess25 = periodExpired * 25 / 100;
//        this.freshNess75 = periodExpired * 75 / 100;
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

    public long getFreshNess25() {
        return freshNess25;
    }

    public long getFreshNess75() {
        return freshNess75;
    }

}

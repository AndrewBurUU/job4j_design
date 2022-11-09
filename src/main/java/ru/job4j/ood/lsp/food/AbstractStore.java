package ru.job4j.ood.lsp.food;

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

    public AbstractStore(String name) {
        this.name = name;
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
    public Food add(Food food, LocalDate localDate) {
        this.periodExpired = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        this.periodOnDate = ChronoUnit.DAYS.between(food.getCreateDate(), localDate);
        this.freshNess25 = periodExpired * 25 / 100;
        this.freshNess75 = periodExpired * 75 / 100;
        return food;
    }

    @Override
    public List<Food> findByName(String key) {
        ArrayList<Food> itemsByName = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i) != null && foods.get(i).getName().contains(key)) {
                itemsByName.add(foods.get(i));
            }
        }
        return itemsByName;
    }

    @Override
    public Food findById(int id) {
        int index = indexOf(id);
        return index != -1 ? foods.get(index) : null;
    }

    public List<Food> getFoods() {
        return foods;
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

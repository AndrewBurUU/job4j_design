package ru.job4j.ood.lsp.food;

import java.time.*;
import java.util.*;

public class ControlQuality {

    public Store checkFood(Food food, LocalDate localDate, List<Store> stores) {
        Store res = null;
        for (Store store : stores) {
            if (store.add(food, localDate) != null) {
                res = store;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Store> stores = List.of(
                new WareHouse("wharehouse"),
                new Shop("shop"),
                new Trash("trash")
        );
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        Food food = new Fruit(1, "Apple", createDate, expiryDate, 100, 10);
        ControlQuality controlQuality = new ControlQuality();
        /**WareHouse*/
        LocalDate workDate = LocalDate.of(2022, 10, 3);
        Store store = controlQuality.checkFood(food, workDate, stores);
        System.out.println(store);
        /**Shop*/
        workDate = LocalDate.of(2022, 10, 15);
        store = controlQuality.checkFood(food, workDate, stores);
        System.out.println(store);
        /**Shop discount*/
        workDate = LocalDate.of(2022, 10, 29);
        store = controlQuality.checkFood(food, workDate, stores);
        System.out.println(store);
        /**Trash*/
        workDate = LocalDate.of(2022, 11, 01);
        store = controlQuality.checkFood(food, workDate, stores);
        System.out.println(store);

    }
}

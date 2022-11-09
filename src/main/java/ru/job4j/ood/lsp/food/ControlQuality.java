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
        ControlQuality controlQuality = new ControlQuality();
        /**WareHouse*/
        Food apple = new Fruit("Apple", createDate, expiryDate, 100, 10);
        LocalDate workDate = LocalDate.of(2022, 10, 3);
        controlQuality.checkFood(apple, workDate, stores);
        /**Shop*/
        Food potato = new Vegetable("Potato", createDate, expiryDate, 100, 10);
        workDate = LocalDate.of(2022, 10, 15);
        controlQuality.checkFood(potato, workDate, stores);
        /**Shop discount*/
        Food potatoCheap = new Vegetable("PotatoCheap", createDate, expiryDate, 100, 10);
        workDate = LocalDate.of(2022, 10, 29);
        controlQuality.checkFood(potatoCheap, workDate, stores);
        /**Trash*/
        Food milk = new Milk("Milk", createDate, expiryDate, 100, 10);
        workDate = LocalDate.of(2022, 11, 01);
        controlQuality.checkFood(milk, workDate, stores);

        /**
       WareHouse wareHouse = (WareHouse) stores.get(0);
        List<Food> foods = wareHouse.getFoods();
        foods.forEach(food1 -> System.out.println(food1));
*/

        for (Store store1 : stores) {
            System.out.println(store1.getName());
            /**
            List<Food> foodList = store1.getFoods();
            foodList.forEach(food -> System.out.println(food));
            foodList = store1.findByName("Potato");
            foodList.forEach(food -> System.out.println(food));
            */
            System.out.println(store1.findById(1));
        }
    }
}

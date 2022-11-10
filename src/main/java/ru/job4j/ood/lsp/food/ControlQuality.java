package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;
import ru.job4j.ood.lsp.food.store.*;

import java.time.*;
import java.util.*;

public class ControlQuality {

    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public Store checkFood(Food food, List<Store> stores) {
        Store res = null;
        for (Store store : stores) {
            if (store.add(food)) {
                res = store;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ControlQuality controlQuality = new ControlQuality(List.of(
                new WareHouse("wharehouse"),
                new Shop("shop"),
                new Trash("trash")
        ));
/**        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);*/
        /**WareHouse*/
        LocalDate createDate = LocalDate.of(2022, 11, 9);
        LocalDate expiryDate = LocalDate.of(2022, 11, 30);
        Food apple = new Fruit("Apple", createDate, expiryDate, 100, 10);
        controlQuality.checkFood(apple, controlQuality.stores);
        /**Shop*/
        createDate = LocalDate.of(2022, 11, 1);
        expiryDate = LocalDate.of(2022, 11, 30);
        Food potato = new Vegetable("Potato", createDate, expiryDate, 100, 10);
        controlQuality.checkFood(potato, controlQuality.stores);
        /**Shop discount*/
        createDate = LocalDate.of(2022, 11, 1);
        expiryDate = LocalDate.of(2022, 11, 12);
        Food potatoCheap = new Vegetable("PotatoCheap", createDate, expiryDate, 100, 10);
        controlQuality.checkFood(potatoCheap, controlQuality.stores);
        /**Trash*/
        createDate = LocalDate.of(2022, 11, 1);
        expiryDate = LocalDate.of(2022, 11, 9);
        Food milk = new Milk("Milk", createDate, expiryDate, 100, 10);
        controlQuality.checkFood(milk, controlQuality.stores);

       /**
       WareHouse wareHouse = (WareHouse) controlQuality.stores.get(0);
        List<Food> foods = wareHouse.getAll();
        foods.forEach(food1 -> System.out.println(food1));
*/

        for (Store store1 : controlQuality.stores) {
            System.out.println(store1);
            List<Food> foodList = store1.getAll();
            foodList.forEach(food -> System.out.println(food));
        }
    }
}

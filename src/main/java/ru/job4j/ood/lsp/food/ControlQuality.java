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

    public Store checkFood(Food food, LocalDate localDate, List<Store> stores) {
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
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        /**WareHouse*/
        Food apple = new Fruit("Apple", createDate, expiryDate, 100, 10);
        LocalDate workDate = LocalDate.of(2022, 10, 3);
        controlQuality.checkFood(apple, workDate, controlQuality.stores);
        /**Shop*/
        Food potato = new Vegetable("Potato", createDate, expiryDate, 100, 10);
        workDate = LocalDate.of(2022, 10, 15);
        controlQuality.checkFood(potato, workDate, controlQuality.stores);

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

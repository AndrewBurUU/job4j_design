package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

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

    public void resort() {
        List<Store> tmpStores = new ArrayList<>();
        for (Store store : stores) {
            List<Food> foods = store.getAll();
            for (Food food : foods) {
                checkFood(food, tmpStores);
            }
        }
        stores = tmpStores;
    }

}

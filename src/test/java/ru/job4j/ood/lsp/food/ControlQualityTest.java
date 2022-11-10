package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.*;
import ru.job4j.ood.lsp.food.model.*;
import ru.job4j.ood.lsp.food.store.*;

import java.time.*;
import java.util.*;

@Disabled
class ControlQualityTest {

    private List<Store> stores = List.of(
            new WareHouse("wharehouse"),
            new Shop("shop"),
            new Trash("trash")
    );

    @Test
    public void whenPutToWareHouse() {
        String expected = "wharehouse";
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food apple = new Fruit(1, "Apple", createDate, expiryDate, 100, 10);
        Store res = controlQuality.checkFood(apple, stores);
/**        assertThat(res.getName()).isEqualTo(expected);*/
    }

    @Test
    public void whenPutToShop() {
        String expected = "shop";
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food potato = new Vegetable(1, "Potato", createDate, expiryDate, 100, 10);
        Store res = controlQuality.checkFood(potato, stores);
/**        assertThat(res.getName()).isEqualTo(expected);*/
    }

    @Test
    public void whenPutToShopWithDiscount() {
        int expected = 90;
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food potatoCheap = new Vegetable(1, "PotatoCheap", createDate, expiryDate, 100, 10);
        controlQuality.checkFood(potatoCheap, stores);
        Shop shop = (Shop) stores.get(1);
/**        Food res = shop.findById(1);
       assertThat(res.getPrice()).isEqualTo(expected);*/
    }

    @Test
    public void whenPutToTrash() {
        String  expected = "trash";
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food milk = new Milk(1, "Milk", createDate, expiryDate, 100, 10);
        Store res = controlQuality.checkFood(milk, stores);
/**        assertThat(res.getName()).isEqualTo(expected);*/
    }

}
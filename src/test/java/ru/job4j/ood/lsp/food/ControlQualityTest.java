package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

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
        ControlQuality controlQuality = new ControlQuality();
        Food apple = new Fruit(1, "Apple", createDate, expiryDate, 100, 10);
        LocalDate workDate = LocalDate.of(2022, 10, 3);
        Store res = controlQuality.checkFood(apple, workDate, stores);
        assertThat(res.getName()).isEqualTo(expected);
    }

    @Test
    public void whenPutToShop() {
        String expected = "shop";
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        ControlQuality controlQuality = new ControlQuality();
        Food potato = new Vegetable(1, "Potato", createDate, expiryDate, 100, 10);
        LocalDate workDate = LocalDate.of(2022, 10, 15);
        Store res = controlQuality.checkFood(potato, workDate, stores);
        assertThat(res.getName()).isEqualTo(expected);
    }

    @Test
    public void whenPutToShopWithDiscount() {
        int expected = 90;
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        ControlQuality controlQuality = new ControlQuality();
        Food potatoCheap = new Vegetable(1, "PotatoCheap", createDate, expiryDate, 100, 10);
        LocalDate workDate = LocalDate.of(2022, 10, 29);
        controlQuality.checkFood(potatoCheap, workDate, stores);
        Shop shop = (Shop) stores.get(1);
        Food res = shop.findById(1);
        assertThat(res.getPrice()).isEqualTo(expected);
    }

    @Test
    public void whenPutToTrash() {
        String  expected = "trash";
        LocalDate createDate = LocalDate.of(2022, 10, 1);
        LocalDate expiryDate = LocalDate.of(2022, 10, 31);
        ControlQuality controlQuality = new ControlQuality();
        Food milk = new Milk(1, "Milk", createDate, expiryDate, 100, 10);
        LocalDate workDate = LocalDate.of(2022, 11, 01);
        Store res = controlQuality.checkFood(milk, workDate, stores);
        assertThat(res.getName()).isEqualTo(expected);
    }

}
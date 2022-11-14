package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.*;
import ru.job4j.ood.lsp.food.model.*;
import ru.job4j.ood.lsp.food.store.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    private Store warehouse = new WareHouse(new LocalDateExpirationCalculator());
    private Store shop = new Shop(new LocalDateExpirationCalculator());
    private Store trash = new Trash(new LocalDateExpirationCalculator());
    private List<Store> stores = List.of(warehouse, shop, trash);

    @Test
    public void whenPutToWareHouse() {
        LocalDate localDate = LocalDate.now();
        LocalDate createDate = localDate.minusDays(1);
        LocalDate expiryDate = localDate.plusDays(30);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food apple = new Fruit("Apple", createDate, expiryDate, 100, 10);
        Store res = controlQuality.checkFood(apple, stores);
        assertThat(res).isEqualTo(warehouse);
    }

    @Test
    public void whenPutToShop() {
        LocalDate localDate = LocalDate.now();
        LocalDate createDate = localDate.minusDays(localDate.getDayOfMonth());
        LocalDate expiryDate = localDate.plusDays(30);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food potato = new Vegetable("Potato", createDate, expiryDate, 100, 10);
        Store res = controlQuality.checkFood(potato, stores);
        assertThat(res).isEqualTo(shop);
    }

    @Test
    public void whenPutToShopWithDiscount() {
        int expected = 90;
        LocalDate localDate = LocalDate.now();
        LocalDate createDate = localDate.minusDays(localDate.getDayOfMonth());
        LocalDate expiryDate = localDate.plusDays(2);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food potatoCheap = new Vegetable("PotatoCheap", createDate, expiryDate, 100, 10);
        controlQuality.checkFood(potatoCheap, stores);
        Food res = shop.getAll().get(0);
        assertThat(res.getPrice()).isEqualTo(expected);
    }

    @Test
    public void whenPutToTrash() {
        LocalDate localDate = LocalDate.now();
        LocalDate createDate = localDate.minusDays(localDate.getDayOfMonth());
        LocalDate expiryDate = localDate.minusDays(1);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food milk = new Milk("Milk", createDate, expiryDate, 100, 10);
        Store res = controlQuality.checkFood(milk, stores);
        assertThat(res).isEqualTo(trash);
    }

    @Test
    public void whenPutFourFoodsToAllStores() {
        WareHouse expectedWarehouse = new WareHouse(new LocalDateExpirationCalculator());
        Shop expectedShop = new Shop(new LocalDateExpirationCalculator());
        Trash expectedTrash = new Trash(new LocalDateExpirationCalculator());
        List<Store> expected = List.of(expectedWarehouse, expectedShop, expectedTrash);

        LocalDate localDate = LocalDate.now();
        LocalDate createDate = localDate.minusDays(1);
        LocalDate expiryDate = localDate.plusDays(30);
        ControlQuality controlQuality = new ControlQuality(stores);
        /**Warehouse*/
        Food apple = new Fruit("Apple", createDate, expiryDate, 100, 10);
        expectedWarehouse.add(apple);
        controlQuality.checkFood(apple, stores);
        /**Shop*/
        createDate = localDate.minusDays(localDate.getDayOfMonth());
        expiryDate = localDate.plusDays(30);
        Food potato = new Vegetable("Potato", createDate, expiryDate, 100, 10);
        expectedShop.add(potato);
        controlQuality.checkFood(potato, stores);
        /**Shop discount*/
        createDate = localDate.minusDays(localDate.getDayOfMonth());
        expiryDate = localDate.plusDays(2);
        Food potatoCheap = new Vegetable("PotatoCheap", createDate, expiryDate, 100, 10);
        expectedShop.add(potatoCheap);
        controlQuality.checkFood(potatoCheap, stores);
        /**Trash*/
        createDate = localDate.minusDays(localDate.getDayOfMonth());
        expiryDate = localDate.minusDays(1);
        Food milk = new Milk("Milk", createDate, expiryDate, 100, 10);
        expectedTrash.add(milk);
        controlQuality.checkFood(milk, stores);
        assertThat(stores).isEqualTo(expected);
    }

}
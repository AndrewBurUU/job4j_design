package ru.job4j.ood.lsp.food.model;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class Fruit extends Food {

    public Fruit(int id, String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(id, name, createDate, expiryDate, price, discount);
    }

    public Fruit(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }

}

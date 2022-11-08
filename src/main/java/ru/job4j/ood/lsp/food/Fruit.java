package ru.job4j.ood.lsp.food;

import java.time.*;

public class Fruit extends Food {

    public Fruit(int id, String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(id, name, createDate, expiryDate, price, discount);
    }
}

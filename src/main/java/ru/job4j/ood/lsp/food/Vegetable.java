package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class Vegetable extends Food {

    public Vegetable(int id, String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(id, name, createDate, expiryDate, price, discount);
    }

    public Vegetable(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }

}

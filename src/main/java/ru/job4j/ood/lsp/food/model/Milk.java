package ru.job4j.ood.lsp.food.model;

import ru.job4j.ood.lsp.food.model.*;

import java.time.*;

public class Milk extends Food {

    public Milk(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }

}

package ru.job4j.ood.lsp.food;

import java.time.*;

public class Milk extends Food {

    public Milk(int id, String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(id, name, createDate, expiryDate, price, discount);
    }

    public Milk(String name, LocalDate createDate, LocalDate expiryDate, int price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }

}

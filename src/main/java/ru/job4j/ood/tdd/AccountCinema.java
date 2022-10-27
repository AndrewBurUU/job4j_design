package ru.job4j.ood.tdd;

import java.util.*;

public class AccountCinema implements Account {

    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(date.getTime());
    }
}

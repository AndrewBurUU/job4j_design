package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.*;

import java.util.*;

public interface Store {

    List<Food> getAll();

    boolean add(Food food);
}

package ru.job4j.ood.lsp.food;

import java.time.*;
import java.util.*;

public interface Store {

    String getName();

    List<Food> getFoods();

    List<Food> findByName(String key);

    Food findById(int id);

    Food add(Food food, LocalDate localDate);
}

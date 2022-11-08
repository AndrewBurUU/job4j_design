package ru.job4j.ood.lsp.food;

import java.time.*;

public interface Store {

    Food add(Food food, LocalDate localDate);
}

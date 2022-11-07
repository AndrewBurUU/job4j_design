package ru.job4j.ood.lsp.homework.example2;

import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TrainingDays implements Iterable<Integer> {

    private Map<LocalDate, Integer> lessonDays = new LinkedHashMap<>();

    public void add(LocalDate date, int hours) {
        lessonDays.put(date, hours);
    }

    @Override
    public Iterator<Integer> iterator() {
        return lessonDays.values().iterator();
    }
}

package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.formatter.*;
import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.*;

import java.util.*;
import java.util.function.*;

public class ReportJSON implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser, Gson gson) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append(gson.toJson(employee));
        }
        return text.toString();
    }

}

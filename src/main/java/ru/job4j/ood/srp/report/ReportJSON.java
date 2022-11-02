package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.*;

import java.util.function.*;

public class ReportJSON implements Report {

    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var gson = new GsonBuilder().create();
        return gson.toJson(store.findBy(filter));
    }

}

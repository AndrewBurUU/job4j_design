package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.*;

import java.util.function.*;

public class ReportJSON implements Report {

    private final Store store;
    private final Gson gson;

    public ReportJSON(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }

}

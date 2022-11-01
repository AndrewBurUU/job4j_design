package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.*;

import java.util.function.*;

public class ReportIT implements Report {

    private final Store store;

    public ReportIT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

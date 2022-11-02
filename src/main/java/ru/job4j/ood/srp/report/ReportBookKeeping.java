package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.*;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.*;
import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.*;

import java.util.*;
import java.util.function.*;

public class ReportBookKeeping implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;

    public ReportBookKeeping(Store store,
                             DateTimeParser<Calendar> dateTimeParser,
                             CurrencyConverter converter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double salary = converter.convert(Currency.USD, employee.getSalary(), Currency.RUB);
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

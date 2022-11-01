package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.*;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.*;

import java.util.function.*;

public class ReportBookKeeping implements Report {

    private final Store store;
    private final Currency currencyFrom;
    private final Currency currencyTo;

    public ReportBookKeeping(Store store,
                             Currency currencyFrom,
                             Currency currencyTo) {
        this.store = store;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            InMemoryCurrencyConverter inMemoryCurrencyConverter = new InMemoryCurrencyConverter();
            double salary = inMemoryCurrencyConverter.convert(currencyFrom, employee.getSalary(), currencyTo);
            text.append(employee.getName()).append(" ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

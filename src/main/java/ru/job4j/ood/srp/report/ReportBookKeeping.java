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
    private final Currency currencyFrom;
    private final Currency currencyTo;

    public ReportBookKeeping(Store store,
                             DateTimeParser<Calendar> dateTimeParser,
                             Currency currencyFrom,
                             Currency currencyTo) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            InMemoryCurrencyConverter inMemoryCurrencyConverter = new InMemoryCurrencyConverter();
            double salary = inMemoryCurrencyConverter.convert(currencyFrom, employee.getSalary(), currencyTo);
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan",
                new GregorianCalendar(2020, Calendar.OCTOBER, 01),
                new GregorianCalendar(2021, Calendar.OCTOBER, 01),
                100);
        store.add(worker);
        worker = new Employee("Piter",
                new GregorianCalendar(2020, Calendar.NOVEMBER, 01),
                new GregorianCalendar(2021, Calendar.NOVEMBER, 01),
                150);
        store.add(worker);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new ReportBookKeeping(store,
                parser,
                Currency.USD,
                Currency.RUB);
        String res = engine.generate(em -> true);
        System.out.println(res);
    }
}

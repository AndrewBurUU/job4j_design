package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.*;
import ru.job4j.io.*;
import ru.job4j.ood.srp.currency.*;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenCurrencyConverter() {
        double expected = 6500;
        InMemoryCurrencyConverter inMemoryCurrencyConverter = new InMemoryCurrencyConverter();
        double res = inMemoryCurrencyConverter.convert(Currency.USD, 100, Currency.RUB);
        assertThat(expected).isEqualTo(res);
    }

    @Test
    public void whenHRRepGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        worker = new Employee("Piter", now, now, 150);
        store.add(worker);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Peter").append(" ")
                .append(150)
                .append(System.lineSeparator())
                .append("Ivan").append(" ")
                .append(100);
        Report engine = new ReportEngine(store, parser, Collections.reverseOrder());
        /**String res = engine.generateReverseOrder("Name; Salary;",em -> true);*/
        String res = engine.generate(em -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }

    @Test
    public void whenItRepGenerated() {
        CSVReader expected = new CSVReader();
        CSVReader res = new CSVReader();
        assertThat(expected).isEqualTo(res);
    }
}
package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;
import ru.job4j.io.*;
import ru.job4j.ood.srp.currency.*;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.io.*;
import java.nio.file.*;
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
        Employee worker = new Employee("Ivan", null, null, 100);
        store.add(worker);
        worker = new Employee("Piter", null, null, 150);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Peter").append(" ")
                .append(150)
                .append(System.lineSeparator())
                .append("Ivan").append(" ")
                .append(100);
        Report engine = new ReportEngine(store, null);
        String res = engine.generate(em -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }

    @Test
    public void whenItRepGenerated(@TempDir Path folder) throws Exception {
        String expectedData = String.join(
                System.lineSeparator(),
                "Name; Hired; Fired; Salary;",
                "Ivan; 2021.10.01; 2022.10.01; 1000;",
                "Peter; 2000.10.01; 2022.10.31; 2000;"
        );
        File expectedFile = folder.resolve("source.csv").toFile();
        Files.writeString(expectedFile.toPath(), expectedData);

        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee(
                "Ivan",
                new GregorianCalendar(2021, 10, 01),
                new GregorianCalendar(2022, 10, 01),
                1000);
        store.add(worker);
        worker = new Employee(
                "Peter",
                new GregorianCalendar(2000, 10, 01),
                new GregorianCalendar(2022, 10, 31),
                2000);
        store.add(worker);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new ReportEngine(store, parser);
        String resData = engine.generate(em -> true);
        File resFile = folder.resolve("target.csv").toFile();
        Files.writeString(resFile.toPath(), resData);
        assertThat(expectedFile).isEqualTo(resFile);
    }
}
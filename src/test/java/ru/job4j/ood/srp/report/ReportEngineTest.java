package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

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
    public void whenBookKeepingReportGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", null, null, 100);
        store.add(worker);
        worker = new Employee("Piter", null, null, 150);
        store.add(worker);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Ivan").append(" ")
                .append(6500.0)
                .append(System.lineSeparator())
                .append("Piter").append(" ")
                .append(9750.0)
                .append(System.lineSeparator());
        Report engine = new ReportBookKeeping(store, Currency.USD, Currency.RUB);
        String res = engine.generate(em -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }

    @Test
    public void whenHRRepGenerated() {
        MemStore store = new MemStore();
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
        Report engine = new ReportHR(store);
        String res = engine.generate(em -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }

    @Test
    public void whenItRepGenerated(@TempDir Path folder) throws Exception {
        StringBuilder expected = new StringBuilder()
                .append("Name;Salary")
                .append(System.lineSeparator())
                .append("Ivan;1000.0")
                .append(System.lineSeparator())
                .append("Peter;2000.0")
                .append(System.lineSeparator());
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", null, null, 1000);
        store.add(worker);
        worker = new Employee("Peter", null, null, 2000);
        store.add(worker);
        Report engine = new ReportIT(store);
        String res = engine.generate(em -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }
}
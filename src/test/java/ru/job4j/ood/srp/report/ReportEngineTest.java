package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;
import ru.job4j.ood.srp.currency.*;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.nio.file.*;
import java.text.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

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
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append("Ivan").append(" ")
                .append("01.10.2020").append(" ")
                .append("01.10.2021").append(" ")
                .append(6500.0)
                .append(System.lineSeparator())
                .append("Piter").append(" ")
                .append("01.11.2020").append(" ")
                .append("01.11.2021").append(" ")
                .append(9750.0)
                .append(System.lineSeparator());
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
        InMemoryCurrencyConverter inMemoryCurrencyConverter = new InMemoryCurrencyConverter();
        Report engine = new ReportBookKeeping(store,
                parser, inMemoryCurrencyConverter);
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
                .append("Piter").append(" ")
                .append(150.0)
                .append(System.lineSeparator())
                .append("Ivan").append(" ")
                .append(100.0)
                .append(System.lineSeparator());
        Report engine = new ReportHR(store);
        String res = engine.generate(em -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }

    @Test
    public void whenItRepGenerated(@TempDir Path folder) throws Exception {
        StringBuilder expected = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append("Ivan;01.10.2020;01.10.2021;100.0")
                .append(System.lineSeparator())
                .append("Piter;01.11.2020;01.11.2021;150.0")
                .append(System.lineSeparator());
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
        Report engine = new ReportIT(store, parser);
        String res = engine.generate(employee -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }

    @Test
    public void whenJSONRepGenerated(@TempDir Path folder) throws Exception {
        StringBuilder expected = new StringBuilder()
                .append("[{\"name\":\"Ivan\",\"salary\":100.0},")
                .append("{\"name\":\"Peter\",\"salary\":150.0}]");
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", null, null, 100);
        store.add(worker);
        worker = new Employee("Peter", null, null, 150);
        store.add(worker);
        Report engine = new ReportJSON(store);
        String res = engine.generate(em -> true);
        assertThat(expected.toString()).isEqualTo(res);
    }

    @Test
    public void whenXMLRepGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = formatter.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store);
        String res = engine.generate(em -> true);
        StringJoiner expected = new StringJoiner("\n");
        expected.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        expected.add("<report>");
        expected.add("    <employees>");
        expected.add(String.format("        <fired>%s</fired>", date));
        expected.add(String.format("        <hired>%s</hired>", date));
        expected.add("        <name>Ivan</name>");
        expected.add("        <salary>100.0</salary>");
        expected.add("    </employees>");
        expected.add("</report>");
        expected.add("");
        assertThat(expected.toString()).isEqualTo(res);
    }
}
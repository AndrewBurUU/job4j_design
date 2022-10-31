package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private static final Comparator<Employee> COMPARATOR = Comparator.comparingDouble(Employee::getSalary).reversed();

    public ReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    /**
    public String generateReverseOrder(String fields, Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(fields)
                .append(System.lineSeparator());
        List<Employee> employeeList = store.findBy(filter);
        Collections.sort(employeeList, Collections.reverseOrder());
        for (Employee employee : employeeList) {
            if (fields.contains("Name")) {
                text.append(employee.getName()).append(" ");
            }
            if (fields.contains("Hired")) {
                text.append(dateTimeParser.parse(employee.getHired())).append(" ");
            }
            if (fields.contains("Fired")) {
                text.append(dateTimeParser.parse(employee.getFired())).append(" ");
            }
            if (fields.contains("Salary")) {
                text.append(employee.getSalary());
            }
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
*/

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employeeList = store.findBy(filter);
        Collections.sort(employeeList, COMPARATOR);
        for (Employee employee : employeeList) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}

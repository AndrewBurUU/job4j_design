package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private final Store store;
    private static final Comparator<Employee> COMPARATOR = Comparator.comparingDouble(Employee::getSalary).reversed();

    public ReportHR(Store store) {
        this.store = store;
    }

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

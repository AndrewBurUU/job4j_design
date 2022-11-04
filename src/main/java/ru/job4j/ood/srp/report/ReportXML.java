package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.*;
import ru.job4j.ood.srp.store.*;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;
import java.util.function.*;

public class ReportXML implements Report {

    private final Store store;
    private JAXBContext context;
    private Marshaller marshaller;

    public ReportXML(Store store) {
        this.store = store;
        try {
            this.context = JAXBContext.newInstance(Employees.class);
            this.marshaller = context.createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement(name = "report")
    public static class Employees {

        private List<Employee> employees;

        public Employees() {
        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String res = "";
        try {
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(store.findBy(filter)), writer);
                res = writer.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return res;
    }
}

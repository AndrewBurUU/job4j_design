package ru.job4j.ood.dip.example;

public class ConsolePrinter implements Printer {

    private final static String PRINTER_TYPE = "ConsolePrinter";

    @Override
    public void print(String text) {
        System.out.println(String.format("%s. %s", PRINTER_TYPE, text));
    }
}

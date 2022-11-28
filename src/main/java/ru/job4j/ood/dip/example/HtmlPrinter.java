package ru.job4j.ood.dip.example;

public class HtmlPrinter implements Printer {

    private final static String PRINTER_TYPE = "HTMLPrinter";

    @Override
    public void print(String text) {
        System.out.println(String.format("%s. %s", PRINTER_TYPE, text));
    }
}

package ru.job4j.ood.dip.example;

/**
 * Модифицированный класс с учетом DIP.
 */
public class Book {

    private String name;
    private Printer printer;


    public Book(String name, Printer printer) {
        this.name = name;
        this.printer = printer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public void print() {
        printer.print(name);
    }

    public static void main(String[] args) {
        Book book = new Book("BookName", new ConsolePrinter());
        book.print();
        book.setPrinter(new HtmlPrinter());
        book.print();
    }
}

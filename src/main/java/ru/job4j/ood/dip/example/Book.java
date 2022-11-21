package ru.job4j.ood.dip.example;

/**
 * Нарушение принципа DIP на уровне поля: абстракция печати книги не отделена от деталей класса ConsolePrinter.
 * Нарушение принципа DIP на уровне вывода информации: в методе Print выводится конкретное сообщение.
 * Нарушение принципа DIP на уровне входных параметров: в конструкторе указан класс вывода данных только на консоль,
 * хотя должна быть возможность вывода информации и в файл, и на печать.
 */
public class Book {
    String name;
    ConsolePrinter printer;

    public Book(String name, ConsolePrinter printer) {
        this.name = name;
        this.printer = printer;
    }

    public void print() {
        printer.print("Вывод в консоль: " + name);
    }
}

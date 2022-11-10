package ru.job4j.ood.lsp.homework.example1;

public class Tram extends RailwayTransport {

    public Tram(int countItems) {
        super(countItems);
    }

    public void status() {
        if (countItems <= 0) {
            throw new IllegalArgumentException("No wagons!");
        }
        /**Нарушение предусловия*/
        if (countItems > 3) {
            throw new IllegalArgumentException("Too much wagons!");
        }
        System.out.println("Norm");
    }
}


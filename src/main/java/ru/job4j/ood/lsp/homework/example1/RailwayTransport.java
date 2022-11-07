package ru.job4j.ood.lsp.homework.example1;

public class RailwayTransport {

    protected int countItems;

    public RailwayTransport(int countItems) {
        this.countItems = countItems;
    }

    public void status() {
        if (countItems <= 0) {
            throw new IllegalArgumentException("No wagons!");
        }
        if (countItems == 1) {
            throw new IllegalArgumentException("Only locomotive?!");
        }
        System.out.println("Norm");
    }

    public int getCountItems() {
        return countItems;
    }

    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }
}

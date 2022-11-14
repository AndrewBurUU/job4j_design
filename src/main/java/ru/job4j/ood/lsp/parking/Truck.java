package ru.job4j.ood.lsp.parking;

public class Truck implements Transport {

    private String model;
    private String number;
    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public int getTransportSize() {
        return size;
    }
}

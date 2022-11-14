package ru.job4j.ood.lsp.parking;

public class Car implements Transport {

    public static final int SIZE = 1;

    private String model;
    private String number;

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
        return SIZE;
    }
}

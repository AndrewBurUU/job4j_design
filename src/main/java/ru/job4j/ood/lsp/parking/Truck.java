package ru.job4j.ood.lsp.parking;

public class Truck implements Transport {

    public static final String TYPE = "TRUCK";

    private String model;
    private String number;
    private int size;

    public Truck(String model, String number, int size) {
        this.model = model;
        this.number = number;
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

package ru.job4j.ood.lsp.parking;

public class Car implements Transport {

    public static final int SIZE = 1;
    public static final String TYPE = "CAR";

    private String model;
    private String number;

    public Car(String model, String number) {
        this.model = model;
        this.number = number;
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
        return SIZE;
    }
}

package ru.job4j.ood.lsp.parking;

import java.util.*;

public class Car implements Transport {

    public static final int SIZE = 1;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, number);
    }
}

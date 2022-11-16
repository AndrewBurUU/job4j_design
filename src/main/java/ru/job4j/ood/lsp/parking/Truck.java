package ru.job4j.ood.lsp.parking;

import java.util.*;

public class Truck implements Transport {

    private String model;
    private String number;
    private int size;

    public Truck(String model, String number, int size) {
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size && Objects.equals(model, truck.model) && Objects.equals(number, truck.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, number, size);
    }
}

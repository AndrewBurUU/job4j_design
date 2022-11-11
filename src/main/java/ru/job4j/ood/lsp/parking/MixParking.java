package ru.job4j.ood.lsp.parking;

public class MixParking implements Parking {

    @Override
    public ParkSpace parkSpace() {
        return null;
    }

    @Override
    public ParkSpace add(Transport transport) {
        return null;
    }

    @Override
    public void remove(Transport transport) {

    }

    @Override
    public int[] getFreeSpaces(Transport transport) {
        return new int[0];
    }

    @Override
    public String getModel(String model) {
        return null;
    }

    @Override
    public String getNumber(String number) {
        return null;
    }

    @Override
    public int getTransportSize(int size) {
        return 0;
    }
}

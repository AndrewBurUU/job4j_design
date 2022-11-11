package ru.job4j.ood.lsp.parking;

public class MixParking implements Parking{

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
}

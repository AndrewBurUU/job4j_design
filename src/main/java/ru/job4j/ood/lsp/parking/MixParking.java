package ru.job4j.ood.lsp.parking;

import java.util.*;

/**
 * carFreeSpaces - кол-во свободных мест для парковки легковых машин
 * truckFreeSpaces - кол-во свободных мест для парковки грузовых машин
 * carList - список легковых машин на парковке
 * truckList - список грузовых машин на парковке
 */

public class MixParking implements Parking {

    private int carFreeSpaces;
    private int truckFreeSpaces;
    private List<Transport> carList;
    private List<Transport> truckList;

    public MixParking(int carFreeSpaces, int truckFreeSpaces) {
        this.carFreeSpaces = carFreeSpaces;
        this.truckFreeSpaces = truckFreeSpaces;
        this.carList = new ArrayList<>(carFreeSpaces);
        this.truckList = new ArrayList<>(truckFreeSpaces);
    }

    @Override
    public void add(Transport transport) {
    }

    @Override
    public void remove(Transport transport) {
    }

}

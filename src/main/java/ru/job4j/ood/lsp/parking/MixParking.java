package ru.job4j.ood.lsp.parking;

import java.util.*;

/**
 * легковая машина занимает только 1 место
 * грузовая машина занимает более 1 места (2 и т.д.)
 * carFreeSpaces - кол-во свободных мест для парковки легковых машин
 * truckFreeSpaces - кол-во свободных мест для парковки грузовых машин
 * carList - список легковых машин на парковке
 * truckList - список грузовых машин на парковке
 */

public class MixParking implements Parking {

    private int carFreeSpaces;
    private int truckFreeSpaces;
    private Set<Transport> carList;
    private Set<Transport> truckList;

    public MixParking(int carFreeSpaces, int truckFreeSpaces) {
        this.carFreeSpaces = carFreeSpaces;
        this.truckFreeSpaces = truckFreeSpaces;
        this.carList = new HashSet<>(carFreeSpaces);
        this.truckList = new HashSet<>(truckFreeSpaces);
    }

    @Override
    public boolean add(Transport transport) {
        int transportSize = transport.getTransportSize();
        if (transportSize == Car.SIZE
                && carFreeSpaces >= Car.SIZE) {
            carList.add(transport);
            carFreeSpaces--;
            return true;
        }
        if (transportSize > Car.SIZE
                && truckFreeSpaces >= Car.SIZE) {
            truckList.add(transport);
            truckFreeSpaces = truckFreeSpaces - transportSize;
            return true;
        }
        if (transportSize > Car.SIZE
                && carFreeSpaces >= transportSize) {
            truckList.add(transport);
            carFreeSpaces = carFreeSpaces - transportSize;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Transport transport) {
        if (carList.contains(transport)) {
            carList.remove(transport);
           carFreeSpaces = carFreeSpaces + transport.getTransportSize();
           return true;
        }
        if (truckList.contains(transport)) {
            truckList.remove(transport);
            truckFreeSpaces = truckFreeSpaces + transport.getTransportSize();
            return true;
        }
        return false;
    }

}

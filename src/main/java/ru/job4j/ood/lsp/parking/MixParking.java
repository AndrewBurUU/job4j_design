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
    private List<Transport> carList;
    private List<Transport> truckList;

    public MixParking(int carFreeSpaces, int truckFreeSpaces) {
        this.carFreeSpaces = carFreeSpaces;
        this.truckFreeSpaces = truckFreeSpaces;
        this.carList = new ArrayList<>(carFreeSpaces);
        this.truckList = new ArrayList<>(truckFreeSpaces);
    }

    @Override
    public boolean add(Transport transport) {
        boolean res = false;
        int transportSize = transport.getTransportSize();
        if (transportSize == 1) {
            if (carFreeSpaces > 0) {
                carList.add(transport);
                carFreeSpaces--;
                res = true;
            }
        } else {
            if (truckFreeSpaces > 0) {
                truckList.add(transport);
                truckFreeSpaces = truckFreeSpaces - transportSize;
                res = true;
            } else if (carFreeSpaces >= transportSize) {
                truckList.add(transport);
                carFreeSpaces = carFreeSpaces - transportSize;
                res = true;
            }
        }
        return res;
    }

    @Override
    public boolean remove(Transport transport) {
        boolean res = false;
        if (carList.contains(transport)) {
           carList.remove(transport);
           carFreeSpaces = carFreeSpaces + transport.getTransportSize();
           res = true;
        } else if (truckList.contains(transport)) {
            truckList.remove(transport);
            truckFreeSpaces = truckFreeSpaces + transport.getTransportSize();
            res = true;
        }
        return res;
    }

}

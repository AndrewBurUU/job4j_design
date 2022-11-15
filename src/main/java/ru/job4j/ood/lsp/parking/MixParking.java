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
    public boolean add(Transport transport) {
        boolean res = false;
        if ("car".equals(transport.getModel())) {
            if (carFreeSpaces > 0) {
                carList.add(transport);
                carFreeSpaces--;
                res = true;
            }
        } else if ("truck".equals(transport.getModel())) {
            int truckSize = transport.getTransportSize();
            if (truckFreeSpaces > 0) {
                truckList.add(transport);
                truckFreeSpaces = truckFreeSpaces - truckSize;
                res = true;
            } else if (carFreeSpaces >= truckSize) {
                truckList.add(transport);
                carFreeSpaces = carFreeSpaces - truckSize;
                res = true;
            }
        }
        return res;
    }

    @Override
    public boolean remove(Transport transport) {
        return false;
    }

}

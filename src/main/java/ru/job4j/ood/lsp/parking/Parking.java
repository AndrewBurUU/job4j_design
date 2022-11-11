package ru.job4j.ood.lsp.parking;

import java.util.*;

public interface Parking extends Transport {

    Map<Transport, Integer> parkSpaceLimit = new HashMap<>();
    ParkSpace parkSpace();
    List<Transport> parkCarSpaces = new ArrayList<>();
    List<Transport> parkTruckSpaces = new ArrayList<>();

    ParkSpace add(Transport transport);
    void remove(Transport transport);
    int[] getFreeSpaces(Transport transport);

}

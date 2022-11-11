package ru.job4j.ood.lsp.parking;

import java.util.*;

public interface Parking extends Transport {

    Map<Transport, Integer> PARK_SPACE_LIMIT = new HashMap<>();

    ParkSpace parkSpace();

    List<Transport> PARK_CAR_SPACES = new ArrayList<>();

    List<Transport> PARK_TRUCK_SPACES = new ArrayList<>();

    ParkSpace add(Transport transport);

    void remove(Transport transport);

    int[] getFreeSpaces(Transport transport);

}

package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@Disabled
class MixParkingTest {

    @Test
    void whenTwoCarsOneTruck() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport car2 = new Car("Mercedes", "к520аа");
        Transport truck = new Truck("Kamaz", "а111аа", 1);
        List<Transport> expectedCarList = List.of(car1, car2);
        List<Transport> expectedTruckList = List.of(truck);
        MixParking expectedMixParking = new MixParking(5, 2);
        /**expectedMixParking.carList = expectedCarList;
        expectedMixParking.truckList = expectedTruckList;*/

        MixParking mixParking = new MixParking(5, 2);
        mixParking.add(car1);
        mixParking.add(car2);
        mixParking.add(truck);

        assertThat(expectedMixParking).isEqualTo(mixParking);
    }
}
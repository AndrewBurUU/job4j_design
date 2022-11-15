package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@Disabled
class MixParkingTest {

    @Test
    void whenOnlyCars() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport car2 = new Car("Mercedes", "к520аа");
        Transport truck = new Truck("Kamaz", "а111аа", 6);
        MixParking mixParking = new MixParking(5, 0);
        assertThat(mixParking.add(car1)).isTrue();
        assertThat(mixParking.add(car2)).isTrue();
        assertThat(mixParking.add(truck)).isFalse();
    }

    @Test
    void whenTwoCarsOneTruck() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport car2 = new Car("Mercedes", "к520аа");
        Transport truck = new Truck("Kamaz", "а111аа", 1);
        MixParking mixParking = new MixParking(5, 2);
        assertThat(mixParking.add(car1)).isTrue();
        assertThat(mixParking.add(car2)).isTrue();
        assertThat(mixParking.add(truck)).isTrue();
    }

    @Test
    void whenTwoCarsAndTruckToCarParking() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport car2 = new Car("Mercedes", "к520аа");
        Transport truck = new Truck("Kamaz", "а111аа", 1);
        MixParking mixParking = new MixParking(5, 0);
        assertThat(mixParking.add(car1)).isTrue();
        assertThat(mixParking.add(car2)).isTrue();
        assertThat(mixParking.add(truck)).isTrue();
    }

    @Test
    void whenOnlyTrucks() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport truck1 = new Truck("Kamaz", "а111аа", 1);
        Transport truck2 = new Truck("Kamaz", "а111аа", 2);
        MixParking mixParking = new MixParking(0, 2);
        assertThat(mixParking.add(car1)).isFalse();
        assertThat(mixParking.add(truck1)).isTrue();
        assertThat(mixParking.add(truck2)).isTrue();
    }

    @Test
    void whenOnlyTrucksToBothParking() {
        Transport truck1 = new Truck("Kamaz", "а111аа", 1);
        Transport truck2 = new Truck("Kamaz", "а111аа", 2);
        MixParking mixParking = new MixParking(2, 1);
        assertThat(mixParking.add(truck1)).isTrue();
        assertThat(mixParking.add(truck2)).isTrue();
    }

}
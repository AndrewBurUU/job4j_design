package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

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
        Transport truck = new Truck("Kamaz", "а111аа", 2);
        MixParking mixParking = new MixParking(5, 2);
        assertThat(mixParking.add(car1)).isTrue();
        assertThat(mixParking.add(car2)).isTrue();
        assertThat(mixParking.add(truck)).isTrue();
    }

    @Test
    void whenTwoCarsAndTruckToCarParking() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport car2 = new Car("Mercedes", "к520аа");
        Transport truck = new Truck("Kamaz", "а111аа", 2);
        MixParking mixParking = new MixParking(5, 0);
        assertThat(mixParking.add(car1)).isTrue();
        assertThat(mixParking.add(car2)).isTrue();
        assertThat(mixParking.add(truck)).isTrue();
    }

    @Test
    void whenOnlyTrucks() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport truck1 = new Truck("Kamaz", "а111аа", 2);
        Transport truck2 = new Truck("Maz", "в222вв", 2);
        MixParking mixParking = new MixParking(0, 4);
        assertThat(mixParking.add(car1)).isFalse();
        assertThat(mixParking.add(truck1)).isTrue();
        assertThat(mixParking.add(truck2)).isTrue();
    }

    @Test
    void whenOnlyTrucksToBothParking() {
        Transport truck1 = new Truck("Kamaz", "а111аа", 2);
        Transport truck2 = new Truck("Maz", "в222вв", 2);
        MixParking mixParking = new MixParking(2, 4);
        assertThat(mixParking.add(truck1)).isTrue();
        assertThat(mixParking.add(truck2)).isTrue();
    }

    @Test
    void whenRemoveCarsAndTruck() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport car2 = new Car("Mercedes", "к520аа");
        Transport truck = new Truck("Kamaz", "а111аа", 2);
        MixParking mixParking = new MixParking(5, 2);
        mixParking.add(car1);
        mixParking.add(car2);
        mixParking.add(truck);
        assertThat(mixParking.remove(car1)).isTrue();
        assertThat(mixParking.remove(car2)).isTrue();
        assertThat(mixParking.remove(truck)).isTrue();
    }

    @Test
    void whenRemoveCarsButNoTruck() {
        Transport car1 = new Car("Lada", "в777аа");
        Transport car2 = new Car("Mercedes", "к520аа");
        Transport truck = new Truck("Kamaz", "а111аа", 2);
        MixParking mixParking = new MixParking(2, 0);
        mixParking.add(car1);
        mixParking.add(car2);
        mixParking.add(truck);
        assertThat(mixParking.remove(car1)).isTrue();
        assertThat(mixParking.remove(car2)).isTrue();
        assertThat(mixParking.remove(truck)).isFalse();
    }

}
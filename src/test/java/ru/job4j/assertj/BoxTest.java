package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void whereNumberOfVerticesIsZero() {
        Box box = new Box(0, 10);
        int res = box.getNumberOfVertices();
        assertThat(res).isEqualTo(0);
    }

    @Test
    void whereNumberOfVerticesIsSix() {
        Box box = new Box(6, 10);
        int res = box.getNumberOfVertices();
        assertThat(res).isEqualTo(6);
    }

    @Test
    void isExist() {
        Box box = new Box(6, 10);
        boolean res = box.isExist();
        assertThat(res).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(5, 10);
        boolean res = box.isExist();
        assertThat(res).isFalse();
    }

    @Test
    void whereGetAreaIs12dot56() {
        Box box = new Box(0, 1);
        double res = box.getArea();
        assertThat(res).isCloseTo(12.56d, withPrecision(0.06d));
    }

    @Test
    void whereGetAreaIsGreaterThan1dot7() {
        Box box = new Box(4, 1);
        double res = box.getArea();
        assertThat(res).isGreaterThan(1.7d);
    }

}
package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    private List<Integer> integerList = new ArrayList<>();

    @Test
    void checkMaxInt() {
        integerList = List.of(19, 20, 15, 1);
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.max(integerList, (left, right) -> Integer.compare(left, right));
        assertThat(rsl).isEqualTo(20);
    }

    @Test
    void checkMinInt() {
        integerList = List.of(19, 20, 15, 1);
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.min(integerList, (left, right) -> Integer.compare(left, right));
        assertThat(rsl).isEqualTo(1);
    }

    @Test
    void whenListIsEmpty() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(integerList, (left, right) -> Integer.compare(left, right))).isNull();
    }

}
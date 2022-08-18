package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> res = List.of("first", "second", "three", "four", "five");
        assertThat(res).hasSize(5)
                .contains("three")
                .containsOnly("three", "four", "five", "first", "second")
                .containsAnyOf("five")
                .doesNotContain("ten")
                .endsWith("four", "five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> res = Set.of("first", "second", "three", "four", "five");
        assertThat(res).isNotNull()
                .filteredOn(e -> e.contains("three") || e.contains("four") || e.contains("five"))
                .hasSize(3)
                .anySatisfy(e -> {
                    assertThat(e).endsWith("five");
                });
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> res = Map.of(
                "first", 1,
                "second", 2,
                "third", 3);
        assertThat(res).hasSize(3)
                .containsValues(1, 2, 3)
                .doesNotContainKey("ten")
                .containsEntry("second", 2);
    }
}
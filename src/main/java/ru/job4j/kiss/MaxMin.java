package ru.job4j.kiss;

import java.util.*;
import java.util.function.*;

public class MaxMin {

    public <T> T condition(List<T> value, BiPredicate<T, T> predicate) {
        T res;
        if (value.isEmpty()) {
            res = null;
        } else {
            res = value.get(0);
            for (T t: value) {
                if (predicate.test(t, res)) {
                    res = t;
                }
            }
        }
        return res;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return condition(value, (t, max) -> comparator.compare(t, max) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return condition(value, (t, min) -> comparator.compare(t, min) < 0);
    }

}

package ru.job4j.collection;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() - 1 == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (filter.test(t)) {
                iterator.previous();
                iterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (filter.test(t)) {
                iterator.previous();
                iterator.set(value);
            }
        }

    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            for (T el : elements) {
                if (t.equals(el)) {
                    iterator.previous();
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> input;
        input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListIterator<Integer> iterator = input.listIterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i == 3) {
                iterator.previous();
                iterator.set(4);
            }
        }
        input.stream().forEach(System.out::println);
    }
}

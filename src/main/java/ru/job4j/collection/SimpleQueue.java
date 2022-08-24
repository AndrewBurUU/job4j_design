package ru.job4j.collection;

import java.util.*;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in == null) {
            throw new NoSuchElementException();
        }
        return null;
    }

    public void push(T value) {
        in.push(value);
    }

    public static void main(String[] args) {

    }
}

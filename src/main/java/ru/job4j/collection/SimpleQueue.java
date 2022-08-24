package ru.job4j.collection;

import java.util.*;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        if (in == null) {
            throw new NoSuchElementException();
        }
        int count = 0;
        while (count < size) {
            out.push(in.pop());
            count++;
        }

        T res = out.pop();
        size--;
        count = 0;
        while (count < size) {
            in.push(out.pop());
            count++;
        }
        return res;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}

package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int expectedModCount;

    private T[] growUp() {
        container = Arrays.copyOf(container, container.length * 2);
        return container;
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = growUp();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T res = null;
        for (int i = 0; i < container.length; i++) {
            if (i == index) {
                container[i] = newValue;
                res = container[i];
                break;
            }
        }
        return res;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        modCount++;
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return container[index];
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        T res = null;
        for (int i = 0; i < container.length; i++) {
            if (i == index) {
                res = container[i];
                break;
            }
        }
        return res;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        if (expectedModCount != modCount) {
            throw new ConcurrentModificationException();
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return size < container.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[size];
            }
        };
    }
}

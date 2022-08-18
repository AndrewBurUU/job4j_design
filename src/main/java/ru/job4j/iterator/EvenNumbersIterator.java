package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    private void findEvenNumber() {
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        }
    }
    @Override
    public boolean hasNext() {
        findEvenNumber();
        return index < data.length ? true : false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return index < data.length ? data[index++] : 0;
    }
}

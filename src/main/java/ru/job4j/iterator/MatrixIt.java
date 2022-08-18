package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean res = true;
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        if (row == data.length ||
                (row == data.length - 1 && column == data[row].length)
        ) {
            res = false;
        }
        return res;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer res = 0;
        if (column < data[row].length) {
            res = data[row][column++];
        } else {
            row++;
            column = 0;
            while (data[row].length == 0) {
                row++;
            }
            res = data[row][column++];
        }
        return res;
    }
}
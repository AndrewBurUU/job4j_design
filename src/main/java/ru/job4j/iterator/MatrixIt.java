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

    private void getFullRow() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
    }

    @Override
    public boolean hasNext() {
        boolean res = true;
        getFullRow();
        if (row == data.length || (row == data.length - 1 && column == data[row].length)) {
            res = false;
        } else {
            if (column == data[row].length || data[row].length == 0) {
                column = 0;
                row++;
                getFullRow();
            }
        }
        return res;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
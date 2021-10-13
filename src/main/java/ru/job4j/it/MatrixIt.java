package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Последовательно возвращаем все элементы двухмерного массива.
 * row - строка
 * column - столбец
  */

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Если строка ничего не содержит - переходим на следующую строку, переходим в начало строки -
     * делаем проверку чтобы не выйти за пределы массива.
     * @return  возвращаем false если прошли по всем элементам, всех строк, иначе true.
     */
    @Override
    public boolean hasNext() {
        while (data[row].length == 0) {
            row++;
            column = 0;
            if (row >= data.length) {
                break;
            }
        }
        return row < data.length && column < data[row].length;
    }

    /**
     * column++ - переходим на следующий элемент строки
     * Если дошли до конца строки - переходим на новую строку, на первый элемент строки.
     * @return
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column];
        column++;
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return rsl;
    }
}

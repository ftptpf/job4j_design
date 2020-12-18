package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Последовательно возвращаем все элементы двухмерного массива.
  */

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0; // строка
    private int column = 0; // столбец

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == 0) { // если строка ничего не содержит
            row++; // переходим на следующую строку
            column = 0; // переходим в начало строки
            if (row >= data.length) { // делаем проверку чтобы не выйти за пределы массива
                break;
            }
        }
        return row < data.length && column < data[row].length; // возвращаем false если прошли по всем элементам
        // всех строк, иначе true
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column];
        column++; // переходим на следующий элемент строки
        if (column >= data[row].length) { // если дошли до конца строки
            row++; // переходим на новую строку
            column = 0; // на первый элемент строки
        }
        return rsl;
    }
}

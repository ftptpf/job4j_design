package ru.job4j.generics;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] array;
    T sl;

    public SimpleArray(T[] array) {
        this.array = array;
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку.
     * @param model
     */
    public void add(T model) {
        int count = 0;
        int startSize = 10;

        if (array.length == 0) {
            array = (T[]) new Object[startSize];
        }
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(null)) {
                count += 1;
            }
        }
        if (count == 10) {
            startSize = startSize + 10;
            array = (T[]) new Object[startSize];
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = model;
                break;
            }
        }
    }

    /**
     * Метод заменяет указанным элементом (model) элемент, находящийся по индексу index.
     * @param index
     * @param model
     */
    public void set(int index, T model) {
        try {
            Objects.checkIndex(index, array.length);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс за пределами массива");
        }
    }

    /**
     * Метод удаляет элемент по указанному индексу, все находящиеся справа элементы
     * при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек).
     * @param index
     */
    public void remove(int index) {
        try {
            Objects.checkIndex(index, array.length);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс за пределами массива");
        }
        T[] destArray = (T[]) new Object[array.length];
        System.arraycopy(array, 0, destArray, 0, index + 1);
        System.arraycopy(array, index + 1, destArray, index, (array.length - index - 1));
        array = destArray;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     * @param index
     * @return
     */
    public T get(int index) {
        try {
            Objects.checkIndex(index, array.length);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Индекс за пределами массива");
        }
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor; // индекс следующего возвращаемого элемента

        @Override
        public boolean hasNext() {
            return cursor < array.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[cursor++];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleArray<?> that = (SimpleArray<?>) o;
        return sl.equals(that.sl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sl);
    }
}

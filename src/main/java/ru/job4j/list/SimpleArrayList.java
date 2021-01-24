package ru.job4j.list;

import java.util.*;

/**
 * Упрощенная реализация ArrayList, динамического списка на массиве.
 * @param <T>
 */
public class SimpleArrayList<T> implements Iterable<T> {
    Object[] container; // массив элементов
    private static final int DEFAULT_CAPACITY = 10; // константа начальной емкости массива
    private int modCount = 0; // счетчик изменений
    private int size; // колличество добавленных в массив элементов

    public SimpleArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод возвращает элемент расположенный по указанному индексу.
     * В методе выполняется проверка на то чтобы передаваемый index находился в рамках добавленных в массив элементов.
     * @param index
     * @return
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    /**
     * Метод добавляет указанный элемент (model) в первую свободную ячейку.
     * В методе выполняется проверка, если массив уже заполнен элементами
     * - выполняется расширение его емкости еще на 10.
     * @param model
     */
    public void add(T model) {
        modCount++;
        if (size == container.length) {
            Object[] moreSizeContainer = new Object[container.length + DEFAULT_CAPACITY];
            System.arraycopy(container, 0, moreSizeContainer, container.length, container.length);
            container = moreSizeContainer.clone();
        }
        container[size++] = model;
    }

    /**
     * Метод проверяет содержится ли уже передаваемый элемент (model) в массиве.
     * Если "да" возвращает true.
     * Если "нет" возвращает false;
     * @param model
     * @return
     */
    public boolean contains(T model) {
        int count = -1;
        if (Objects.equals(model, null)) {
        //if (model == null) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(container[i], null)) {
                //if (container[i] == null) {
                    count++;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(container[i], model)) {
                //if (container[i] == model) {
                    count++;
                }
            }
        }
        return count != -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleArrayList<?> that = (SimpleArrayList<?>) o;
        return Arrays.equals(container, that.container);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(container);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int expectModCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return cursor != size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else if (expectModCount != modCount) {
                throw  new ConcurrentModificationException();
            }

            return (T) container[cursor++];
        }
    }
}

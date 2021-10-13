package ru.job4j.list;

import java.util.*;

/**
 * Упрощенная реализация ArrayList, динамического списка на массиве.
 * container - массив элементов
 * DEFAULT_CAPACITY - константа начальной емкости массива
 * modCount - счетчик изменений
 * size - количество добавленных в массив элементов
 * @param <T>
 */
public class SimpleArrayList<T> implements Iterable<T> {
    Object[] container;
    private static final int DEFAULT_CAPACITY = 10;
    private int modCount = 0;
    private int size;

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
        for (Object el : container) {
             if (Objects.equals(el, model)) {
                  count++;
                  break;
             }
        }
        return count != -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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

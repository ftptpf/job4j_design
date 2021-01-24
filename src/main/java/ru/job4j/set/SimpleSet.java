package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализация упрощенной коллекции SET.
 * В коллекции могут присутствовать только уникальные элементы в том числе один null.
 * Реализация за счет композиции SimpleArrayList (упрощенного ArrayList).
 * @param <T>
 */
public class SimpleSet<T> implements Iterable<T> {
    private SimpleArrayList<T> list;

    public SimpleSet() {
        list = new SimpleArrayList<>();
    }

    /**
     * Метод добавляет значения в коллекцию.
     * Предварительно выполняется проверка на уникальность добавляемого в коллекцию значения.
     * @param e
     */
    public void add(T e) {
        if (!list.contains(e)) {
            list.add(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return list.equals(simpleSet.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}

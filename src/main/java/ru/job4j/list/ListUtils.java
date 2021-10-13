package ru.job4j.list;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    /**
     * Метод вставляет значение перед индексом.
     * Пока в листе есть следующее элементы, если индекс следующего элемента при вызове next будет равен index,
     * добавляем элемент (value) между элементами вызываемыми next() и previous(), т.е. перед index.
     * @param list
     * @param index
     * @param value
     * @param <T>
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод вставляет значение после индекса.
     * Пока в листе есть следующее элементы, если индекс предыдущего элемента при вызове previous будет равен index,
     * добавляем элемент (value) между элементами вызываемыми next() и previous(), т.е. после index.
     * @param list
     * @param index
     * @param value
     * @param <T>
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.previousIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Метод удаляет все элементы, которые удовлетворяют предикату.
     * @param list
     * @param filter
     * @param <T>
     * @return
     */
    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
            }
        }
        return list;
    }

    /**
     * Метод заменяет все элементы, которые удовлетворяют предикату.
     * @param list
     * @param filter
     * @param value
     * @param <T>
     * @return
     */
    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.set(value);
            }
        }
        return list;
    }

    /**
     * Метод удаляет из списка те элементы, которые есть в elements.
     * @param list
     * @param element
     * @param <T>
     * @return
     */
    public static <T> List<T> removeAll(List<T> list, List<T> element) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (element.contains(listIterator.next())) {
                listIterator.remove();
            }
        }
        return list;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

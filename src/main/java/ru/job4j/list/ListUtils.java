package ru.job4j.list;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    /**
     * Метод вставляет значение перед индексом.
     * @param list
     * @param index
     * @param value
     * @param <T>
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) { // пока в листе есть следующее элементы
            if (i.nextIndex() == index) { // если индекс следующего элемента при вызове next будет равен index
                i.add(value); // добавляем элемент (value) между элементами вызываемыми next() и previous(), т.е. перед index
                break;
            }
            i.next(); // переходим к следующему элементу списка
        }
    }

    /**
     * Метод вставляет значение после индекса.
     * @param list
     * @param index
     * @param value
     * @param <T>
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) { // пока в листе есть следующее элементы
            if (i.previousIndex() == index) { // если индекс предыдущего элемента при вызове previous будет равен index
                i.add(value); // добавляем элемент (value) между элементами вызываемыми next() и previous(), т.е. после index
                break;
            }
            i.next(); // переходим к следующему элементу списка
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
            if (filter.test(list.get(listIterator.nextIndex()))) {
                listIterator.next();
                listIterator.remove();
            } else {
                listIterator.next();
            }
        }
        return null;
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
            if (filter.test(list.get(listIterator.nextIndex()))) {
                listIterator.next();
                listIterator.set(value);
            } else {
                listIterator.next();
            }
        }
        return null;
    }

    /**
     * Метод удаляет из списка те элементы, которые есть в elements.
     * @param list
     * @param element
     * @param <T>
     * @return
     */
    public static <T> List<T> removeAll(List<T> list, List<T> element) {
        for (T e : element) {
            ListIterator<T> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                if (e.equals(list.get(listIterator.nextIndex()))) {
                    listIterator.next();
                    listIterator.remove();
                } else {
                    listIterator.next();
                }
            }
        }
        return null;
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

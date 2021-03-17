package ru.job4j.list;

import java.util.*;

/**
 * Упрощенная реалиация LinkedList.
 * @param <E>
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    private Node<E> first; // первый нод
    private Node<E> last; // последний нод
    private int modCount = 0; // счетчик изменений
    private int size = 0; // колличество добавленных в массив элементов

    /**
     * Метод добавляет указанный элемент (model) в конец списка.
     * @param value
     * @return
     */
    public boolean add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null); // создаем новый нод и размещаем его в конец списка
        last = newNode; // последний нод инициализуем новым нодом
        if (l == null) { // если список был пустым
            first = newNode; // первый нод инициализуем новым нодом
        } else {
            l.next = newNode; // в ином случае в предыдущий нод записываем ссылку на последующий новый последний нод
        }
        size++;
        modCount++;
        return true;
    }

    /**
     * Метод возвращает элемент расположенный по указанному индексу.
     * @param index
     * @return
     */
    public E get(int index) {
        Objects.checkIndex(index, size);
        return node(index).item;
    }

    /**
     * Метод возвращает Node по индексу.
     * @param index
     * @return
     */
    Node<E> node(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private Node<E> lastReturned;
        private Node<E> next = first;
        private int nextIndex;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }
    }
}

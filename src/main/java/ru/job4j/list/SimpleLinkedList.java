package ru.job4j.list;

import java.util.*;

/**
 * Упрощенная реализация LinkedList.
 * first - первый нод
 * last - последний нод
 * modCount - счетчик изменений
 * size - количество добавленных в массив элементов
 * @param <E>
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;
    private int size = 0;

    /**
     * Метод добавляет указанный элемент (model) в конец списка.
     * Создаем новый нод и размещаем его в конец списка.
     * Последний нод инициализируем новым нодом.
     * Если список был пустым, первый нод инициализируем новым нодом.
     * В ином случае в предыдущий нод записываем ссылку на последующий новый последний нод.
     *
     * @param value
     * @return
     */
    public boolean add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
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

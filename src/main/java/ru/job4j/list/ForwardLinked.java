package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Организация односвязного списка.
 * head - первый нод
 * modCount - счетчик изменений
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int modCount = 0;

    /**
     * Метод добавляет указанный элемент (value) в конец списка.
     * Создаем новый нод, если первый нод (head) пустой -  инициализируем первый нод новым нодом.
     * Создаем новый нод, пока у нового нода ссылка на следующий нод не станет равна null (не дошли до конца списка) -
     * идем по цепочке, переприсваивая ноды next значениями последующих нодов, доходим до конца списка.
     * В предпоследнем элементе делаем ссылку на "новый" последний элемент.
     * @param value
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            modCount++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        modCount++;
    }

    /**
     * Метод добавляет указанный элемент (value) в начало списка.
     * @param value
     */
    public void addFirst(T value) {
        Node<T> f = head;
        Node<T> newNode = new Node<T>(value, f);
        head = newNode;
        modCount++;
    }

    /**
     * Метод удаляет первый элемент списка.
     * @return обнуление ссылки первого нода (head) на следующий за ним нод
     */
    public T deleteFirst() {
        Node<T> f = head;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * Метод обнуляет значение и ссылку первого нода
     * element - присваиваем значение первого нода
     * next - второй нод (следующий после head)
     * Обнуляем значение первого нода head. Обнуляем ссылку первого нода head на следующий за ним нод.
     * Инициализируем первый нод (head) вторым нодом (next), т.е. второй нод становится первым.
     * @param f
     * @return
     */
    private T unlinkFirst(Node<T> f) {
        final T element = f.value;
        final Node<T> next = f.next;
        f.value = null;
        f.next = null;
        head = next;
        modCount++;
        return element;
    }

    /**
     * Метод для проверки списка, пустой он или нет.
     * @return
     */
    public boolean emptyList() {
        return head == null;
    }

    /**
     * curNode - текущий нод, инициализируем первым нодом (head)
     * preNode - предыдущий нод
     * nextNode - последующий нод
     * Пока текущий нод содержит какое либо значение - инициализируем следующий нод.
     * В текущем ноде делаем ссылку на предыдущий. Предыдущий нод нод инициализируем текущим.
     * Текущий нод инициализируем последующим, сдвигаемся по списку.
     */
    public void revert() {
        Node<T> curNode = head;
        Node<T> preNode = null;
        Node<T> nextNode = null;

        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        head = preNode;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Односвязный список.
     * @param <T>
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.next = next;
            this.value = value;
        }
    }
}

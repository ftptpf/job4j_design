package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Организация односвязного списка.
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head; // первый нод
    private int modCount = 0; // счетчик изменений

    /**
     * Метод добавляет указанный элемент (value) в конец списка.
     * @param value
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null); // создаем новый нод
        if (head == null) { // если первый нод (head) пустой
            head = node; // инициализируем первый нод новым нодом
            modCount++;
            return;
        }
        Node<T> tail = head; // создаем новый нод
        while (tail.next != null) { // пока у нового нода ссылка на следующий нод не станет равна null (не дошли до конца списка)
            tail = tail.next; // идем по цепочке, переприсваивая ноды next значениями последующих нодов, доходим до конца списка
        }
        tail.next = node; // в предпоследнем элементе делаем ссылку на "новый" последний элемент
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
     * @return
     */
    public T deleteFirst() {
        Node<T> f = head;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f); // обнуление ссылки первого нода (head) на следующий за ним нод
    }

    /**
     * Метод обнуляет значение и ссылку первого нода
     * @param f
     * @return
     */
    private T unlinkFirst(Node<T> f) {
        final T element = f.value; // присваиваем значение первого нода
        final Node<T> next = f.next; // next - второй нод (следующий после head)
        f.value = null; // обнуляем значение первого нода head
        f.next = null; // обнуляем ссылку первого нода head на следующий за ним нод
        head = next; // инициализируем первый нод (head) вторым нодом (next), т.е. второй нод становится первым
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

    public void revert() {
        Node<T> curNode = head; // текущий нод, инициализируем первым нодом (head)
        Node<T> preNode = null; // предыдущий нод
        Node<T> nextNode = null; // последующий нод

        while (curNode != null) { // пока текущий нод содержит какое либо значение
            nextNode = curNode.next; // инициализируем следуюший нод
            curNode.next = preNode; // в текущем ноде делаем ссылку на предыдущий
            preNode = curNode; // предыдущий нод нод инициализируем текущим
            curNode = nextNode; // текущий нод инициализируем последуюшим, сдвигаемся по списку
            modCount++;
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

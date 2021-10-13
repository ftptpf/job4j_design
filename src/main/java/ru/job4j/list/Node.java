package ru.job4j.list;

/**
 * Нод.
 * item - ссылка на элемент
 * next - ссылка на следующий элемент
 * prev - ссылка на предыдущий элемент
 * @param <E>
 */
public class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.prev = prev;
        this.next = next;
        this.item = element;
    }
}

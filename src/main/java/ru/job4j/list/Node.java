package ru.job4j.list;

public class Node<E> {
    E item; // ссылка на элемент
    Node<E> next; // ссылка на следующий элемент
    Node<E> prev; // ссылка на предыдущий элемент

    Node(Node<E> prev, E element, Node<E> next) {
        this.prev = prev;
        this.next = next;
        this.item = element;
    }
}

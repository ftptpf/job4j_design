package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод находит узел по значению parent и добавлять в него дочерний узел со значением child.
     * В методе реализована проверка, что значения child еще нет в дереве а parent есть.
     * Если child есть, то метод возвращает false.
     * @param parent
     * @param child
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        Node<E> childNode = new Node<>(child);
        Node<E> parentNode = new Node<>(parent);
        Node<E> node;
        Optional<Node<E>> eParentNode = findBy(parent);
        eParentNode.



        boolean rsl = false;
        return rsl;
    }

    /**
     * В методе реализован алгоритм обхода в ширину.
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty(); // создаем пустой нод
        Queue<Node<E>> data = new LinkedList<>(); // на базе связанного списка создаем очередь нодов
        data.offer(this.root); // в очередь добавляем корневой нод
        while (!data.isEmpty()) { // пока очередь не пуста
            Node<E> el = data.poll(); // присваиваем ноду el первый элемент очереди
            if (el.value.equals(value)) { // если value значение нода el равно искомому значению
                rsl = Optional.of(el); // искомое значение найдено
                break;
            }
            data.addAll(el.children); //добавляем все элементы ArrayList нода el в очередь
        }
        return rsl;
    }
}

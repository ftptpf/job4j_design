package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс дерева.
 * @param <E>
 */
public interface SimpleTree<E> {
    boolean add(E parent, E child);
    Optional<Node<E>> findBy(E value);

    /**
     * Класс описывает узел дерева. Узел содержит хранимое значение и ссылки на дочерние узлы.
     * @param <E>
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}

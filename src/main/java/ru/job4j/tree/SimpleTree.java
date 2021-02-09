package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        public E getValue() {
            return value;
        }

        public List<Node<E>> getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}

package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

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
        Optional<Node<E>> fParent = findBy(parent); // ищем нод по значению(value) parent
        Optional<Node<E>> fChild = findBy(child); // ищем нод по значению(value) child
        if (fParent.isPresent()) { // если мы находим нод со значением parent
            if (fChild.isEmpty()) { // и если мы не находим нод со значение child
                fParent.get().children.add(new Node<>(child)); //  в ArrayList найденного родительского нода добавляем новый нод с child значением
                return true;
            }
        }
        return false;
    }

    /**
     * В методе реализован алгоритм обхода в ширину.
     * @param value
     * @return
     */
/*    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty(); // создаем пустой нод в обертке Optional
        Queue<Node<E>> data = new LinkedList<>(); // на базе связанного списка создаем пустую очередь нодов
        data.offer(this.root); // в очередь добавляем корневой нод
        while (!data.isEmpty()) { // до тех пор пока у очереди есть ноды
            Node<E> el = data.poll(); // берем присваиваем ноду el первый элемент очереди и сразу удаляем этот элемент из очереди
            if (el.value.equals(value)) { // если value значение нода el равно искомому значению
                rsl = Optional.of(el); // искомое значение найдено
                break;
            }
            data.addAll(el.children); // всех потомков из ArrayList-a текущего нода добавляем в очередь для проверки
        }
        return rsl;
    }*/

    @Override
    public Optional<Node<E>> findBy(E value) {
        // Optional<Node<E>> rsl = Optional.empty();
        Predicate<Node<E>> predicate = el -> el.value.equals(value);
        return findByPredicate(predicate);
    }

    /**
     * Метод проверяет количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное, возвращаем false.
     * @return
     */
    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>(); // на базе связанного списка создаем пустую очередь нодов
        data.offer(this.root); // в очередь добавляем корневой нод
        while (!data.isEmpty()) { // до тех пор пока у очереди есть ноды
            Node<E> el = data.poll(); // берем присваиваем ноду el первый элемент очереди и сразу удаляем этот элемент из очереди
            if (el.children.size() > 2) { // если количество (размер ArrayList-а) потомков у данного нода более двух - это значит что дерево не бинарное
                return false;
            }
            data.addAll(el.children); // всех потомков из ArrayList-a текущего нода добавляем в очередь для проверки
        }
        return true;
    }

    /**
     * Метод поиска по условию.
     * @param condition
     * @return
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl =
                return rsl;
            }
            data.addAll(el.children);
        }
        return null;
    }
}

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
     * Ищем нод по значению(value) parent. Ищем нод по значению(value) child.
     * Если мы находим нод со значением parent и если мы не находим нод со значение child -
     * в ArrayList найденного родительского нода добавляем новый нод с child значением.
     * @param parent
     * @param child
     * @return Если child есть, то метод возвращает false.
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> fParent = findBy(parent);
        Optional<Node<E>> fChild = findBy(child);
        if (fParent.isPresent()) {
            if (fChild.isEmpty()) {
                fParent.get().children.add(new Node<>(child));
                return true;
            }
        }
        return false;
    }

    /**
     * Метод поиска по значению, в нем реализован алгоритм обхода в ширину.
     * predicate - создаем условие проверки - value значение нода el равно искомому значению
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = el -> el.value.equals(value);
        return findByPredicate(predicate);
    }

    /**
     * Метод проверяет количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное, возвращаем false.
     * @return false - дерево не бинарное, true - дерево бинарное
     */
    public boolean isBinary() {
        Predicate<Node<E>> predicate = el -> el.children.size() > 2;
        if (findByPredicate(predicate).isPresent()) {
            return false;
        }
        return true;
    }

    /**
     * Метод поиска по условию.
     * Создаем пустой нод в обертке Optional. На базе связанного списка создаем пустую очередь нодов.
     * В очередь добавляем корневой нод. До тех пор пока у очереди есть ноды -
     * берем присваиваем ноду el первый элемент очереди и сразу удаляем этот элемент из очереди.
     * Выполняем проверку по полученному Predicate. Если проверка true - искомое значение найдено и присваивается rsl
     *  и возвращается rsl найденным значением.
     *  Всех потомков из ArrayList-a текущего нода добавляем в очередь для проверки.
     *
     * @param condition
     * @return
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                return rsl;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}

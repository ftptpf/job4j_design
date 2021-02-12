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
     * @param parent
     * @param child
     * @return Если child есть, то метод возвращает false.
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
     * Метод поиска по значению, в нем реализован алгоритм обхода в ширину.
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = el -> el.value.equals(value); // создаем условие проверки - value значение нода el равно искомому значению
        return findByPredicate(predicate);
    }

    /**
     * Метод проверяет количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное, возвращаем false.
     * @return
     */
    public boolean isBinary() {
        Predicate<Node<E>> predicate = el -> el.children.size() > 2; // создаем условие проверки - количество (размер ArrayList-а) потомков у данного нода более двух
        if (findByPredicate(predicate).isPresent()) { // если по условию выше к нам вернулось не пустое значение
            return false; // дерево не бинарное
        }
        return true; // в ином случае - дерево бинарное
    }

    /**
     * Метод поиска по условию.
     * @param condition
     * @return
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty(); // создаем пустой нод в обертке Optional
        Queue<Node<E>> data = new LinkedList<>(); // на базе связанного списка создаем пустую очередь нодов
        data.offer(this.root); // в очередь добавляем корневой нод
        while (!data.isEmpty()) { // до тех пор пока у очереди есть ноды
            Node<E> el = data.poll(); // берем присваиваем ноду el первый элемент очереди и сразу удаляем этот элемент из очереди
            if (condition.test(el)) { // выполяем проверку по полученному Predicate
                rsl = Optional.of(el); // если проверка true - искомое значение найдено и присваивается rsl
                return rsl; // и возвращается rsl найденным значением
            }
            data.addAll(el.children); // всех потомков из ArrayList-a текущего нода добавляем в очередь для проверки
        }
        return rsl;
    }
}

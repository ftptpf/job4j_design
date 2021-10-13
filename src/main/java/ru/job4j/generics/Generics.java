package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();

        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);

        /**
         * gen.printBoundedWildCard(first); - Animal попадает за пределы верхнего ограничения Predator
         */
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        /**
         * gen.printLowerBoundedWildCard(third); - Tiger попадает за пределы нижнего ограничения Predator
         */
    }

    /**
     * Метод работает без ограничений, в него можно передавать коллекцию, которая хранит любые типы.
     * @param list
     */
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Метод имеет ограничение сверху и ограничен классом Predator.
     * @param list
     */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     * Метод имеет ограничение снизу и ограничен классом Predator.
     * @param list
     */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}

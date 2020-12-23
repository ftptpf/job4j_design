package ru.job4j.generics;

import java.util.*;

public class GenericUsage {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        //list.add(new Person("name", 21, new Date(367000980980L)));
        System.out.println("Количество элементов в списке: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            System.out.println("Текущий элемент: " + s);
        }
    }
    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }

    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println(o);
        }
    }
}

package ru.job4j.generics;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WildCardPrintRsl {
    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, 3, 4, 5);
        new WildCardPrintRsl().printRsl(l);
    }
}

package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    SimpleArrayList list = new SimpleArrayList();

    public void add(T e) {
        list.add(e);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<T> listItr = list.iterator();

            @Override
            public boolean hasNext() {
                return listItr.hasNext();
            }

            @Override
            public T next() {
                return listItr.next();
            }
        };
    }
}

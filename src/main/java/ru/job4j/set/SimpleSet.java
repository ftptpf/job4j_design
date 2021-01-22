package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArrayList<T> list;

    public SimpleSet() {
        list = new SimpleArrayList<>();
    }

    public void add(T e) {
        int count = 0; // индекс проверки уникальности
        while (list.iterator().hasNext()) { // до тех пор пока в list есть последюущее значение
            if (list.iterator().next().equals(e)) { // если "е" равно последующему значению
                count++; // увеличиваем индекс проверки значения
                break;
            }
        }
        if (count == 0) { // если индекс уникальности не изменился, т.е. если в list ранее небыло записано "е"
            list.add(e); // добавляем значение в list
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return list.equals(simpleSet.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}

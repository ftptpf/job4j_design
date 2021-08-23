package ru.job4j.ood.tdd;

import java.util.*;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Iterator<T> iterator = value.iterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (comparator.compare(next, result) > 0) {
                result = next;
            }
        }

        return result;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Iterator<T> iterator = value.iterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (comparator.compare(next, result) < 0) {
                result = next;
            }
        }
        return result;
    }
}

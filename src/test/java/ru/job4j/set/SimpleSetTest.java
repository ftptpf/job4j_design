package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAdd() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        assertThat(set.list.get(0), is(1));
        assertThat(set.list.get(1), is(2));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterator() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> setItr = set.iterator();
        assertThat(setItr.hasNext(), is(true));
        assertThat(setItr.next(), is(1));
        assertThat(setItr.hasNext(), is(true));
        assertThat(setItr.next(), is(2));
        assertThat(setItr.hasNext(), is(false));
        assertThat(setItr.next(), is(3));
    }
}
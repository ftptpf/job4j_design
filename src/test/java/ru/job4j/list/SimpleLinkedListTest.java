package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void addGetTest() {
        SimpleLinkedList <Integer> sll = new SimpleLinkedList<>();
        sll.add(12);
        sll.add(13);
        sll.add(14);
        assertThat(sll.get(0), is(12));
        assertThat(sll.get(1), is(13));
        assertThat(sll.get(2), is(14));
    }

    @Test
    public void addIteratorTest() {
        SimpleLinkedList <Integer> sll = new SimpleLinkedList<>();
        sll.add(12);
        sll.add(13);
        Iterator<Integer> itr = sll.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(12));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(13));
        assertThat(itr.hasNext(), is(false));
    }


}
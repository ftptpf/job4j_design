package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void iteratorTestInteger() {
        Integer[] inArray = {1, 2, 3};
        SimpleArray<Integer> it = new SimpleArray<>(inArray);
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().hasNext(), is(true));
    }

    @Test
    public void iteratorTestString() {
        String[] inArray = {"one", "two", "three"};
        SimpleArray<String> it = new SimpleArray<>(inArray);
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().next(), is("one"));
        assertThat(it.iterator().next(), is("one"));
        assertThat(it.iterator().next(), is("one"));
        assertThat(it.iterator().hasNext(), is(true));
    }

    @Test
    public void getTest() {
        Integer[] inArray = {1, 2, 3};
        SimpleArray<Integer> it = new SimpleArray<>(inArray);
        assertThat(it.get(0), is(1));
        assertThat(it.get(1), is(2));
        assertThat(it.get(2), is(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getTestIndexOut() {
        Integer[] inArray = {1, 2, 3};
        SimpleArray<Integer> it = new SimpleArray<>(inArray);
        it.get(3);
    }

    @Test
    public void removeTestInteger() {
        Integer[] inArray = {1, 2, 3};
        SimpleArray<Integer> it = new SimpleArray<>(inArray);
        it.remove(1);
        assertThat(it.get(0), is(1));
        assertThat(it.get(1), is(3));
        // assertThat(it.get(2), is(0)); -- null
    }

    @Test
    public void addTestIntegerEmpty() {
        Integer[] inArray = {};
        SimpleArray<Integer> it = new SimpleArray<>(inArray);
        it.add(10);
        assertThat(it.get(0), is(10));
        assertThat(it.get(1), is(0)); // null
        assertThat(it.get(9), is(0)); // null
    }

    @Test
    public void addTestStringEmpty() {
        String[] inArray = {};
        SimpleArray<String> it = new SimpleArray<>(inArray);
        it.add("one");
        assertThat(it.get(0), is("one"));
        assertNull(it.get(1));
        assertNull(it.get(9));
    }


}
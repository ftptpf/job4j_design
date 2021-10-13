package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void iteratorTestInteger() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(1);
        it.add(2);
        it.add(3);
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().next(), is(1));
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().next(), is(2));
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().next(), is(3));
        assertThat(it.iterator().hasNext(), is(false));
    }

    @Test
    public void iteratorTestString() {
        SimpleArray<String> it = new SimpleArray<>(3);
        it.add("one");
        it.add("two");
        it.add("three");
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().hasNext(), is(true));
        assertThat(it.iterator().next(), is("one"));
        assertThat(it.iterator().next(), is("two"));
        assertThat(it.iterator().next(), is("three"));
        assertThat(it.iterator().hasNext(), is(false));
    }

    @Test
    public void getIntegerTest() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(1);
        it.add(2);
        it.add(3);
        assertThat(it.get(0), is(1));
        assertThat(it.get(1), is(2));
        assertThat(it.get(2), is(3));
    }

    @Test
    public void getStringTest() {
        SimpleArray<String> it = new SimpleArray<>(3);
        it.add("one");
        it.add("two");
        it.add("three");
        assertThat(it.get(0), is("one"));
        assertThat(it.get(1), is("two"));
        assertThat(it.get(2), is("three"));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getTestIndexOut() {
        SimpleArray<Integer> it = new SimpleArray<>(1);
        it.add(1);
        it.get(3);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeTestInteger() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(1);
        it.add(2);
        it.add(3);
        it.remove(1);
        assertThat(it.get(0), is(1));
        assertThat(it.get(1), is(3));
        assertNull(it.get(2)); /** --- java.lang.IndexOutOfBoundsException: Index 2 out of bounds for length 2 */
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void addTestIntegerEmpty() {
        SimpleArray<Integer> it = new SimpleArray<>(0);
        it.add(10);
        assertThat(it.get(0), is(10));
        assertNull(it.get(1)); /** --- java.lang.IndexOutOfBoundsException: Index 1 out of bounds for length 1 */
    }

    @Test
    public void testIteratorEmpty() {
        SimpleArray<Integer> it = new SimpleArray<>(0);
        assertThat(it.iterator().hasNext(), is(false));
    }
    @Test
    public void setIntegerTest() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(1);
        it.add(2);
        it.add(3);
        assertThat(it.get(0), is(1));
        assertThat(it.get(1), is(2));
        assertThat(it.get(2), is(3));
        it.set(1, 10);
        assertThat(it.get(1), is(10));
    }
}

package ru.job4j.it;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenIteratorTest {

    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new EvenIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers3() {
        it = new EvenIterator(new int[]{1, 3, 7});
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void  shouldReturnTrueIfSecondEvenNumbers() {
        it = new EvenIterator(new int[]{1, 4, 1});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));

    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenIterator(new int[] {2, 4, 6, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
    }

    @Test
    public void nextNextFalse() {
        it = new EvenIterator(new int[] {2, 4, 1, 1});
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeUnsupported() {
        it = new EvenIterator(new int[] {2, 4, 1, 1});
        it.remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void forEachRemainingUnsupported() {
        it = new EvenIterator(new int[] {2, 4, 1, 1});
        Consumer<Integer> printer = x -> System.out.println("message: " + x);
        it.forEachRemaining(printer);
    }
}

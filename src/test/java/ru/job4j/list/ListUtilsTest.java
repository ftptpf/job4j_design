package ru.job4j.list;

import org.junit.Test;
import org.hamcrest.core.Is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> one = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> two = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        ListUtils.removeAll(one, two);
        assertThat(Arrays.asList(1, 2), Is.is(one));
    }

    @Test
    public void whenRemoveAllNull() {
        List<Integer> one = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> two = new ArrayList<>();
        ListUtils.removeAll(one, two);
        assertThat(Arrays.asList(1, 2, 3, 4, 5), Is.is(one));
    }

    @Test
    public void whenRemoveAllDouble() {
        List<Integer> one = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 5));
        List<Integer> two = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        ListUtils.removeAll(one, two);
        assertThat(Arrays.asList(1, 2), Is.is(one));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> one = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 5));
        Predicate<Integer> intPredicate = (e) -> e > 2;
        ListUtils.removeIf(one, intPredicate);
        assertThat(Arrays.asList(1, 2), Is.is(one));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> one = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 5));
        Predicate<Integer> intPredicate = (e) -> e > 2;
        ListUtils.replaceIf(one, intPredicate, 11);
        assertThat(Arrays.asList(1, 2, 11, 11, 11, 11), Is.is(one));
    }
}
package ru.job4j.ood.tdd;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;


public class MaxMinTest {

    @Test
    public void checkMaxIntegerResult() {
        MaxMin checkMax = new MaxMin();
        List<Integer> list = List.of(8, 12, 24, 16, 2, 60);
        Comparator<Integer> integerComparator = Integer::compareTo;
        int result = checkMax.max(list, integerComparator);
        assertThat(result, is(60));
    }
    @Test
    public void checkMinIntegerResult() {
        MaxMin checkMin = new MaxMin();
        List<Integer> list = List.of(8, 12, 24, 16, 2, 60);
        Comparator<Integer> integerComparator = Integer::compareTo;
        int result = checkMin.min(list, integerComparator);
        assertThat(result, is(2));
    }
    @Test
    public void checkMaxStringResult() {
        MaxMin checkMax = new MaxMin();
        List<String> list = List.of("text", "and", "support", "a");
        Comparator<String> stringComparator = String::compareTo; // Lexicographically
        String result = checkMax.max(list, stringComparator);
        assertThat(result, is("text"));
    }
    @Test
    public void checkMinStringResult() {
        MaxMin checkMin = new MaxMin();
        List<String> list = List.of("text", "and", "support", "a");
        Comparator<String> stringComparator = String::compareTo; // Lexicographically
        String result = checkMin.min(list, stringComparator);
        assertThat(result, is("a"));
    }
}
package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void insert() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112,"one");
        shm.insert(1113, "two");
        Iterator<Integer> itr = shm.iterator();
    }
}
package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void insertGet() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112,"one");
        shm.insert(1113, "two");
        assertThat(shm.get(1112), is("one"));
        assertThat(shm.get(1113), is("two"));
    }

    @Test
    public void insertDelete() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112,"one");
        shm.insert(1113, "two");
        assertThat(shm.delete(1112), is(true));
        assertThat(shm.delete(2222), is(false));
    }

    @Test
    public void insertTwoTheSame() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112,"one");
        shm.insert(1112,"some");
        assertThat(shm.get(1112), is("one"));
        assertThat(shm.insert(333, "Tom"), is(true));
        assertThat(shm.insert(333, "Bill"), is(false));
    }

    @Test
    public void testIterator() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1115,"one");
        shm.insert(1119,"two");
        Iterator<NodeMap<Integer, String>> it = shm.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next().getValue(), is("one"));
        assertThat(it.next().getValue(), is("two"));
        assertFalse(it.hasNext());
    }

    @Test
    public void insertResize() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        int s = 1122;
        String str = "one";
        for (int i = 0; i < 30; i++) {
            shm.insert(s, str);
            s = s * 2 % 17;
            str += i;
        }
    }
}
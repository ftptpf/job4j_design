package ru.job4j.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void insertGet() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112, "one");
        shm.insert(1113, "two");
        assertThat(shm.get(1112), is("one"));
        assertThat(shm.get(1113), is("two"));
    }

    @Test
    public void insertDelete() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112, "one");
        shm.insert(1113, "two");
        assertThat(shm.delete(1112), is(true));
        assertThat(shm.delete(2222), is(false));
    }

    @Test
    public void insertTwoTheSame() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112, "one");
        shm.insert(1112, "some");
        assertThat(shm.get(1112), is("one"));
        assertThat(shm.insert(333, "Tom"), is(true));
        assertThat(shm.insert(333, "Bill"), is(false));
    }

    @Test
    public void testIterator() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1115, "one");
        shm.insert(1119, "two");
        Iterator<NodeMap<Integer, String>> it = shm.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next().getValue(), is("one"));
        assertThat(it.next().getValue(), is("two"));
        assertFalse(it.hasNext());
    }

    @Test
    public void insertResize() {
        SimpleHashMap<Integer, String> shm = new SimpleHashMap<>();
        shm.insert(1112, "some1112");
        shm.insert(1113, "some1113");
        shm.insert(1114, "some1114");
        shm.insert(1115, "some1115");
        shm.insert(1116, "some1116");
        shm.insert(1117, "some1117");
        shm.insert(1118, "some1118");
        shm.insert(1119, "some1119");
        shm.insert(1120, "some1120");
        shm.insert(1121, "some1121");
        shm.insert(1122, "some1122");
        shm.insert(1123, "some1123This");
        shm.insert(1124, "some1124");
        shm.insert(1125, "some1125");
        shm.insert(1126, "some1126");
        shm.insert(1127, "some1127");
        shm.insert(1128, "some1128");
        shm.insert(1129, "some1129");
        shm.insert(1130, "some1130");
        shm.insert(1131, "some1131");
        shm.insert(1132, "some1132");
        shm.insert(1133, "some1133");
    }
}
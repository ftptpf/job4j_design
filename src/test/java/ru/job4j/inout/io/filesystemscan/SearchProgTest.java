package ru.job4j.inout.io.filesystemscan;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchProgTest {

    @Test
    public void searchFileS() throws IOException {
        Path path = Paths.get("resources/filesystemscan");
        Predicate<Path> predicate = p -> p.toFile().getName().startsWith("t");
        List<Path> rsl = SearchProg.search(path, predicate);
        List<Path> list = new ArrayList<>();

        Path one = Paths.get("resources/filesystemscan", "temp1.txt");
        Path two = Paths.get("resources/filesystemscan", "temp2.txt");

        list.add(one);
        list.add(two);

        assertTrue(rsl.size() == 2 && rsl.containsAll(list));
    }
}
package ru.job4j.inout.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchProgTest {

    @Test
    public void searchFileS() throws IOException {
        Path path = Paths.get("C:", "projects", "job4j_design", "resources");
        Predicate<Path> predicate = p -> p.toFile().getName().startsWith("s");
        List<Path> rsl = SearchProg.search(path, predicate);
        List<Path> list = new ArrayList<>();
        list.add(Paths.get("C:\\projects\\job4j_design\\resources\\serverlog.txt"));
        list.add(Paths.get("C:\\projects\\job4j_design\\resources\\serverlog.zip"));
        list.add(Paths.get("C:\\projects\\job4j_design\\resources\\servernotwork.txt"));
        assertThat(rsl, is(list));
    }
}
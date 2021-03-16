package ru.job4j.inout.io;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchProgTest {

    @Test
    public void searchSFile() throws IOException {
        Path path = Paths.get(".", "projects");
        Predicate<Path> predicate = p -> p.toFile().getName().startsWith("S");
        //SearchProg sp = new SearchProg();
        List<Path> rsl = SearchProg.search(path, predicate);
        assertThat(rsl.toString(), is("sd"));



    }
}
package ru.job4j.inout.io;

import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DuplicatesFinderTest {

    @Test
    public void main() throws IOException {
        Path start = Paths.get("resources");
        Path one = Path.of("resources", "1", "temp.txt");
        Path two = Path.of("resources", "2", "temp.txt");
        /*String resultStr =
                "{FileProperty{name='temp.txt', size=53}=[C:\\projects\\job4j_design\\resources\\1\\temp.txt, C:\\projects\\job4j_design\\resources\\2\\temp.txt]}";*/
        String resultStr = "{FileProperty{name='temp.txt'}=["
                + one.toAbsolutePath().toString()
                + ", "
                + two.toAbsolutePath().toString()
                + "]}";


        Map<FileProperty, List<Path>> mapDuplicates = DuplicatesFinder.findDuplicate(start);
        List<Path> pathList = List.of(one, two);

        /* Выполняем проверку что у нас один ключ и он соответствует определенному значению */
        assertEquals(1, mapDuplicates.keySet().size());
        FileProperty fileProperty = new FileProperty("temp.txt", one.toFile().length());
        assertTrue(mapDuplicates.containsKey(fileProperty));

        /* Выполняем проверку значений и их соответствия */
        assertEquals(2, mapDuplicates.get(fileProperty).size());
        assertTrue(mapDuplicates.get(fileProperty).contains(one.toAbsolutePath()));
        assertTrue(mapDuplicates.get(fileProperty).contains(two.toAbsolutePath()));
    }
}

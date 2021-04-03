package ru.job4j.inout.io;

import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DuplicatesFinderTest {

    @Test
    public void main() throws IOException {
        Path start = Paths.get("resources");
        String resultStr =
                "{FileProperty{name='temp.txt', size=53}=[C:\\projects\\job4j_design\\resources\\1\\temp.txt, C:\\projects\\job4j_design\\resources\\2\\temp.txt]}";

        String str = DuplicatesFinder.findDuplicate(start).toString();
        assertThat(str, is(resultStr));
    }
}

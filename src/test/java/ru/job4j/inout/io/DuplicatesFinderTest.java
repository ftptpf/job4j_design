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
        Path start = Paths.get("C:", "Temp");
        String resultStr =
                "{FileProperty{name='temp.txt', size=0}=[C:\\Temp\\1\\temp.txt, C:\\Temp\\3\\temp.txt]}";

        String str = DuplicatesFinder.findDuplicate(start).toString();
        assertThat(str, is(resultStr));
    }
}

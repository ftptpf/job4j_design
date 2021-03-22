package ru.job4j.inout.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DuplicatesFinderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void main() throws IOException {
        Scanner in = new Scanner(System.in);
        File target = folder.newFile("resultduplicates.txt");
        //Path start = Paths.get("C:", "Temp");
        Path start = Paths.get(in.toString());
        StringBuilder resultSB = new StringBuilder();

        DuplicatesFinder duplicatesFinder = new DuplicatesFinder();
        String str = duplicatesFinder.findDuplicate(start).toString();

        BufferedWriter out = new BufferedWriter(new FileWriter(target));
        out.write(str);
        assertThat(str, is(resultSB));
    }
}

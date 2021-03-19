package ru.job4j.inout.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class DuplicatesFinderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void main() throws IOException {
        File target = folder.newFile("resultduplicates.txt");
        Path start = Paths.get("C:", "Temp");
        StringBuilder resultSB = new StringBuilder();

        DuplicatesFinder duplicatesFinder = new DuplicatesFinder();
        String str = duplicatesFinder.findDuplicate(start).toString();

        BufferedWriter out = new BufferedWriter(new FileWriter(target));
        out.write(str);


    }
}
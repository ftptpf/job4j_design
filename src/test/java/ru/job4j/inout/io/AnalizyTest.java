package ru.job4j.inout.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File target = folder.newFile("servernotwork.txt");
        StringBuilder resultFromFile = new StringBuilder();

        Analizy analizy = new Analizy();
        analizy.unavailable("resources/serverlog.txt");
        analizy.writeToFile(target.getAbsolutePath());

        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(resultFromFile::append);
        }
        assertThat(resultFromFile.toString(), is("10:57:01; 10:59:01;11:01:02; 11:02:02;"));
    }
}
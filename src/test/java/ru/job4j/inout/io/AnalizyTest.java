package ru.job4j.inout.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void unavailable() throws IOException {
        //File source = new File("resources/serverlog.txt");
        File target = new File("resources/servernotwork.txt");
        StringBuilder resultFromFile = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(resultFromFile::append);
        }

        Analizy analizy = new Analizy();
        analizy.unavailable("resources/serverlog.txt", "resources/servernotwork.txt");
        assertThat(resultFromFile.toString(), is("10:57:01; 10:59:01 /r/n 11:01:02;11:02:02"));
    }
}
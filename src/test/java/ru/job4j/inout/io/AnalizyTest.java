package ru.job4j.inout.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void unavailable() {
        String source = "resources/serverlog.txt";
        String target = "resources/servernotwork.txt";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
        assertThat(target, is("10:57:01; 10:59:01 /r/n 11:01:02;11:02:02"));
    }
}
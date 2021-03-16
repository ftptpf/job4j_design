package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.*;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new PrintFiles());
    }
}

package ru.job4j.inout.io.file_system_scan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Ищем файлы по по определенному предикату.
 */
public class SearchProg {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        Path start = Paths.get(args[0]);
        if (args.length == 1) {
            throw new IllegalArgumentException("You need to add file parameter.");
        }
        search(start, path -> path.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException("The wrong root directory path.");
        }
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

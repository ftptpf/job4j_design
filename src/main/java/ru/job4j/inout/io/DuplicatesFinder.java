package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ищем дубликаты файлов. Дубликаты – это два файла с одинаковым именем и размером.
 * Путь к начальной директории берем из аргументов командной строки, просматривает все файлы в ней
 * (и всех подпапках и под-под-...папках) и сообщает, если находим дубликаты.
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
    }

    public static Map<FileProperty, List<Path>> findDuplicate(Path start) {

        return new HashMap();
    }
}

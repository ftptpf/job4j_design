package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Ищем дубликаты файлов. Дубликаты – это два файла с одинаковым именем и размером.
 * Путь к начальной директории берем из аргументов командной строки, просматривает все файлы в ней
 * (и всех подпапках и под-под-...папках) и сообщает, если находим дубликаты.
 */
public class DuplicatesFinder {
    /**
     * Метод запуска поиска дубликатов. Начальную папку поиска задаем в командной строке.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Введите путь к директории с которой начнется поиск дубликатов файлов. Например C:\\Temp");
        Scanner inputString = new Scanner(System.in);
        Map<FileProperty, List<Path>> resultMap = findDuplicate(Path.of(inputString.nextLine()));
        System.out.println(resultMap);
    }

    /**
     * Метод ищет дубликаты.
     * @param start
     * @return
     * @throws IOException
     */
    public static Map<FileProperty, List<Path>> findDuplicate(Path start) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(start, duplicatesVisitor);
        return duplicatesVisitor.getDuplicatedMap(); // возвращаем map с дубликатами
    }
}

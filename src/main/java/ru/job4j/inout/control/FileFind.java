package ru.job4j.inout.control;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Обход и выборка файлов по критериям.
 */
public class FileFind extends SimpleFileVisitor<Path> {
    private Predicate<Path> condition; // условие проверки по имени файла
    private List<Path> resultListPath = new ArrayList<>(); // собранные данные
    private String n; // имя файла, маска, либо регулярное выражение.
    private String t; // тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.

    public FileFind(String n, String t) {
        this.n = n;
        this.t = t;
    }

    /**
     * Метод вызываается во время доступа к файлу.
     * Выполняется проверка по условию и аккумулирование информации.
     * @param file
     * @param attrs
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (t.equals("name") && n.equals(file.toFile().getName())) {
            resultListPath.add(file);
        } else if (t.equals("mask")) {
            String regex = n
                    .replace("*", "(\\w|\\d)+")
                    .replace("?", "(\\w|\\d){1}")
                    .replace(".", "\\.");
            if (Pattern.matches(regex, file.toString())) {
                resultListPath.add(file);
            }
        } else if (t.equals("regex")) {
            if (Pattern.matches(n, file.toFile().getName())) {
                resultListPath.add(file);
            }
        }
        return CONTINUE;
    }

    /**
     * Метод выдает собранную информацию.
     * @return
     */
    public List<Path> getPaths() {
        return resultListPath;
    }
}

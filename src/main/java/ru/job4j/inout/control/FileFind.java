package ru.job4j.inout.control;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
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
        condition = p -> p.toFile().getName().equals("n");
        if (t.equals("name") && condition.test(file)) {
            resultListPath.add(file);
        } else if (t.equals("mask")) {
            String regex = n.replace("*", "(\\w|\\d)+").replace("?", "(\\w|\\d){1}");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(file.toString());
            if (matcher.find()) {
                resultListPath.add(file);
            }
        } else if (t.equals("regex")) {
            Pattern pattern = Pattern.compile(n);
            Matcher matcher = pattern.matcher(file.toString());
            if (matcher.find()) {
                resultListPath.add(file);
            }
        } /*else {
            throw new IllegalArgumentException("The wrong parameter in \"-t\"");*/
        //}
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

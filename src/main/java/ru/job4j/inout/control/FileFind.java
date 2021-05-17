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
    private Predicate<Path> condition; // условие проверки
    private List<Path> resultListPath = new ArrayList<>(); // собранные данные
    private String n;
    private String t;

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
            String regex = n.replace("*", "*?").replace("?", ".?");
            Pattern pattern = Pattern.compile(n);
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

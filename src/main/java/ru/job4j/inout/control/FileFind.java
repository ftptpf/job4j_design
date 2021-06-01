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
    private List<Path> resultListPath = new ArrayList<>(); // собранные данные
    private String n; // имя файла, маска, либо регулярное выражение.
    private String t; // тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.

    public FileFind(String n, String t) {
        this.n = n;
        this.t = t;
    }

    /**
     * Метод вызывается во время доступа к файлу.
     * Выполняется проверка по условию и аккумулирование информации.
     * @param file путь к файлу
     * @param attrs атрибуты файла
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (getSearchCondition(t, n).test(file)) {
            resultListPath.add(file);
        }
        return CONTINUE;
    }

    /**
     * Условие отбора фалов.
     * @param type тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
     * @param name имя файла, маска, либо регулярное выражение.
     * @return
     */
    public static Predicate<Path> getSearchCondition(String type, String name) {
        Predicate<Path> pathPredicate = new Predicate<Path>() {
            @Override
            public boolean test(Path path) {
                String regex = name;
                String pathString = path.toFile().getName();
                switch (type) {
                    case ("name"):
                        regex = '^' + name
                                .replace(".", "\\.");
                        break;
                    case ("regex"):
                        break;
                    case ("mask"):
                        regex = name
                                .replace("*", "(\\w|\\d)+")
                                .replace("?", "(\\w|\\d){1}")
                                .replace(".", "\\.");
                    default:
                        throw new IllegalArgumentException("Non correct type of search. Should be: name, mask or regex.");
                        //break;
                }
                return Pattern.matches(regex, pathString);
            }
        };
        return pathPredicate;
    }

    /**
     * Метод выдает собранную информацию.
     * @return
     */
    public List<Path> getPaths() {
        return resultListPath;
    }
}

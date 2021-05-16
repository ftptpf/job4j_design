package ru.job4j.inout.control;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Обход и выборка файлов по критериям.
 */
public class FileFind extends SimpleFileVisitor<Path> {
    private Predicate<Path> condition; // условие проверки
    private List<Path> resultListPath = new ArrayList<>(); // собранные данные

    public FileFind(Predicate<Path> condition) {
        this.condition = condition;
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
        if (condition.test(file)) {
            resultListPath.add(file);
        }
        //System.out.println(file.toAbsolutePath());
        //return super.visitFile(file, attrs);
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

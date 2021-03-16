package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;


public class SearchFiles implements FileVisitor<Path> {
    private Predicate<Path> condition;
    private List<Path> resultListPath = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    /**
     * Метод вызывается перед входом в папку.
     * @param dir
     * @param attrs
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
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
        return CONTINUE;
    }

    /**
     * Метод вызывается если нет доступа к файлу.
     * @param file
     * @param exc
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    /**
     * Метод вызывается если после посещения папки.
     * @param dir
     * @param exc
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
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

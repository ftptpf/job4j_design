package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Выводит содержимое всей директории включая вложенные директории.
 */
public class PrintFiles implements FileVisitor<Path> {
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
     * Выводит на печать абсолютный путь к файлу.
     * @param file
     * @param attrs
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
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
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws  IOException {
        return CONTINUE;
    }
}

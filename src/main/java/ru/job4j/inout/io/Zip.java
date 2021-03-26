package ru.job4j.inout.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Програма архивации определенных файлов выбранной папки.
 * При запуске указывается папка, которую мы хотим архивировать.
 * В качестве ключа передается  расширения файлов, которые не нужно включать в архив.
 * Архив должен сохранять структуру проекта. То есть содержать подпапки.
 */
public class Zip {
    public void packFiles(List<Path> sources, Path target) {
        Predicate<Path> predicate = p -> p.toFile().getName().endsWith("java");
        List<Path> list = new ArrayList<>();
        try {
            list = SearchProg.search(target, predicate);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Метод архивирует один определенный файл.
     * @param source исходный файл
     * @param target zip архив
     */
    public void packSingleFile(File source, File target) {
        /* Создаем исходящий "декоратор" потоков (записываем): zip поток -> буфферизация потока -> поток байтов запись в zip файл target */
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath())); // создается пустой файл source в zip архиве
            /* Создаем входящий "декоратор" потоков (чтение): буфферизация потока <- чтение байтов из фала source */
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes()); // запись содержимого файла source в ранее созданный в zip архиве пустой файл source

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Zip().packSingleFile(
                new File("./resources/serverlog.txt"),
                new File("./resources/serverlog.zip")
        );
/*        Predicate<Path> pstr = p -> p.toFile().getName().endsWith("java");
        new Zip().packFiles();*/
    }
}

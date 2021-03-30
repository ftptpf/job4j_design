package ru.job4j.inout.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 * Для поиска и фильтрации файлов используем класс SearchProg.
 * Для работы с входными аргументами (-d=c:\project\job4j\ -e=*.java -o=project.zip)
 * используем класс ArgsName, которые прописываем в параметрах класса в Intellije IDEA
 */
public class Zip {
    /**
     * Метод архивирует определенную папку, сохраняя всю её структуру.
     * @param sources лист дирректорий и файлов которые мы хотим архивировать
     * @param target файл в который мы архивируем.
     */
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path path : sources) {
                zipOut.putNextEntry(new ZipEntry(path.toString()));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        }



    /**
     * Метод архивирует один определенный файл.
     * @param source исходный файл
     * @param target zip архив
     */
    public void packSingleFile(File source, File target) {
        /* Создаем исходящие потоки (записываем): zip поток -> буфферизация потока -> поток байтов запись в zip файл target */
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath())); // создается пустой файл source в zip архиве
            /* Создаем входящиие потоки (чтение): буфферизация потока <- чтение байтов из фала source */
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes()); // запись содержимого файла source в ранее созданный в zip архиве пустой файл source

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для проверки и запуска методом класса.
     * @param args Входные аргументы (-d=c:\project\job4j\ -e=java -o=/resources/project.zip) указываем в настройках файла в Intellije IDEA.
     */
    public static void main(String[] args) throws IOException {
/*        if (args.length == 0) { // выполняем проверку чтобы массив принимаемых аргументов небыл пуст
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }*/
        new Zip().packSingleFile(
                new File("./resources/serverlog.txt"),
                new File("./resources/serverlog.zip")
        );
        ArgsName mapArgs = ArgsName.of(args); // создаем map (ключ-значение) из входных параметров
        // Predicate<Path> predicate = p -> p.toFile().toString().endsWith(mapArgs.get("e")); // условие отбора файлов
        //Predicate<Path> predicate = path -> path.toFile().isDirectory() || path.endsWith(mapArgs.get("e"));
        Predicate<Path> predicate = path -> !path.toFile().getName().startsWith("s");
        Path rootPath = Paths.get(mapArgs.get("d")); // дирректория которую мы будем архивировать
        List<Path> list = SearchProg.search(rootPath, predicate); // отобранный лист папок и дирректорий
        Zip zip = new Zip();
        Path target = Paths.get(mapArgs.get("o")); // zip файл в который будем все архивировать
        zip.packFiles(list, target); // архивируем дирректорию
    }
}

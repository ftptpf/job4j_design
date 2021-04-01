package ru.job4j.inout.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Программа архивации определенных файлов выбранной папки.
 * При запуске указывается папка, которую мы хотим архивировать.
 * В качестве ключа передается  расширения файлов, которые не нужно включать в архив.
 * Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * Для поиска и фильтрации файлов используем класс SearchProg.
 * Для работы с входными аргументами (-d=c:\project\job4j\ -e=java -o=.\resources\project.zip)
 * которые прописываем в Run Configurations в Intellije IDEA, используем класс ArgsName.
 */
public class Zip {
    /**
     * Метод архивирует определенную папку, сохраняя всю её структуру.
     * @param sources лист директорий и файлов которые мы хотим архивировать
     * @param target файл в который мы архивируем
     */
    public void packFiles(List<Path> sources, Path target) {
/*        if (sources.size() == 0) { // выполняем проверку чтобы лист файлов и папок которые мы получили не был пуст
            throw new IllegalArgumentException("Пустой список архивирования папок, файлов.");
        }*/
        try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            //zipOut.putNextEntry(new ZipEntry(target.toString()));
            for (Path path : sources) {
                zipOut.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream fromFile = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zipOut.write(fromFile.readAllBytes());
                }
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
        /* Создаем исходящие потоки (записываем): zip поток -> буферизация потока -> поток байтов запись в zip файл target */
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath())); // создается пустой файл source в zip архиве
            /* Создаем входящие потоки (чтение): буферизация потока <- чтение байтов из фала source */
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes()); // запись содержимого файла source в ранее созданный в zip архиве пустой файл source

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для запуска и проверки методов класса.
     * @param args Входные аргументы (-d=c:\project\job4j\ -e=java -o=.\resources\project.zip) указываем в настройках файла в Intellije IDEA.
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 0) { // выполняем проверку чтобы массив принимаемых аргументов не был пуст
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        new Zip().packSingleFile(
                new File("./resources/serverlog.txt"),
                new File("./resources/serverlog.zip")
        );

        ArgsName mapArgs = ArgsName.of(args); // создаем map (ключ-значение) из входных параметров
        Predicate<Path> predicate = p -> !p.toFile().getName().endsWith(mapArgs.get("e")); // условие отбора файлов
        Path rootPath = Paths.get(mapArgs.get("d")); // директория которую мы будем архивировать
        List<Path> list = SearchProg.search(rootPath, predicate); // отобранный лист папок и директорий
        Zip zip = new Zip();
        Path target = Paths.get(mapArgs.get("o")); // zip файл в который будем все архивировать
        zip.packFiles(list, target); // архивируем директорию
    }
}

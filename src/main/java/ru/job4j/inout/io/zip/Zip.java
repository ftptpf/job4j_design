package ru.job4j.inout.io.zip;

import ru.job4j.inout.io.namedarguments.ArgsName;
import ru.job4j.inout.io.filesystemscan.SearchProg;

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
 * Для работы с входными аргументами (-d=c:\projects\job4j_design -e=java -o=.\resources\project.zip)
 * которые прописываем в Run Configurations в Intellije IDEA, используем класс ArgsName.
 */
public class Zip {
    /**
     * Метод архивирует определенную папку, сохраняя всю её структуру.
     * @param sources лист директорий и файлов которые мы хотим архивировать
     * @param target файл в который мы архивируем
     */
    public void packFiles(List<Path> sources, Path target) {
        if (sources.size() == 0) {
            throw new IllegalArgumentException("Пустой список архивирования папок, файлов.");
        }
        try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
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
     * Создаем исходящие потоки (записываем): zip поток -> буферизация потока -> поток байтов запись в zip файл target.
     * Создается пустой файл source в zip архиве.
     * Создаем входящие потоки (чтение): буферизация потока <- чтение байтов из фала source.
     * Запись содержимого файла source в ранее созданный в zip архиве пустой файл source.
     * @param source исходный файл
     * @param target zip архив
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для запуска и проверки методов класса.
     * @param args Входные аргументы (-d=c:\projects\job4j_design -e=java -o=.\resources\project.zip) указываем в настройках файла в Intellije IDEA.
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        ArgsName mapArgs = ArgsName.of(args);
        Predicate<Path> predicate = p -> !p.toFile().getName().endsWith(mapArgs.get("e"));
        Path rootPath = Paths.get(mapArgs.get("d"));
        List<Path> list = SearchProg.search(rootPath, predicate);
        Zip zip = new Zip();
        Path target = Paths.get(mapArgs.get("o"));
        zip.packFiles(list, target);
    }
}

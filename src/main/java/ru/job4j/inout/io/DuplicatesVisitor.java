package ru.job4j.inout.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> mapFiles = new HashMap<>(); // в этот map будем собирать данные по всем файлам, дубликатам и не дубликатам
    /**
     * Метод вызывается во время доступа к файлу.
     * Собираем данные по всем файлам дубликатам и не дубликатам.
     * @param file
     * @param attrs
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.getFileName().toString(), Files.size(file));
        List<Path> list;
        if (!mapFiles.containsKey(fileProperty)) { // если ключ ранее не встречался
            list = new ArrayList<>(); // создаем новый лист
        } else { // в ином случае
            list = mapFiles.get(fileProperty); // получаем существующие значения листа
        }
        list.add(file.toAbsolutePath()); // к листу добавляем текущий путь к файлу
        mapFiles.put(fileProperty, list); // добавляем информация в map
        return FileVisitResult.CONTINUE; // продолжаем дальше
    }
    /**
     * Метод выбора дубликатов из собранных данных.
     * @return
     */
    public Map<FileProperty, List<Path>> getDuplicatedMap() {
        Map<FileProperty, List<Path>> result = new HashMap<>();
        for (Map.Entry<FileProperty, List<Path>> mapEntryFiles : mapFiles.entrySet()) {
            if (mapEntryFiles.getValue().size() > 1) { // если в листе больше одного пути к одному и тому же файлу
                result.put(mapEntryFiles.getKey(), mapEntryFiles.getValue()); // добавляем значения в результат
            }
        }
        return result;
    }
}

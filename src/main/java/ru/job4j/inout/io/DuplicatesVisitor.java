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

/**
 * mapFiles - в этот map будем собирать данные по всем файлам, дубликатам и не дубликатам
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> mapFiles = new HashMap<>();
    /**
     * Метод вызывается во время доступа к файлу.
     * Собираем данные по всем файлам дубликатам и не дубликатам.
     * Если ключ ранее не встречался - создаем новый лист. В ином случае - получаем существующие значения листа.
     * К листу добавляем текущий путь к файлу, добавляем информацию в map
     * @param file
     * @param attrs
     * @return продолжаем дальше
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.getFileName().toString(), Files.size(file));
        List<Path> list;
        if (!mapFiles.containsKey(fileProperty)) {
            list = new ArrayList<>();
        } else {
            list = mapFiles.get(fileProperty);
        }
        list.add(file.toAbsolutePath());
        mapFiles.put(fileProperty, list);
        return FileVisitResult.CONTINUE;
    }
    /**
     * Метод выбора дубликатов из собранных данных.
     * Если в листе больше одного пути к одному и тому же файлу - добавляем значения в результат.
     * @return
     */
    public Map<FileProperty, List<Path>> getDuplicatedMap() {
        Map<FileProperty, List<Path>> result = new HashMap<>();
        for (Map.Entry<FileProperty, List<Path>> mapEntryFiles : mapFiles.entrySet()) {
            if (mapEntryFiles.getValue().size() > 1) {
                result.put(mapEntryFiles.getKey(), mapEntryFiles.getValue());
            }
        }
        return result;
    }
}

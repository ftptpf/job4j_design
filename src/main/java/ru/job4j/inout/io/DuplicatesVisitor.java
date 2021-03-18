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
    private Map<FileProperty, List<Path>> mapFiles = new HashMap<>();
    /**
     * Метод вызываается во время доступа к файлу.
     * @param file
     * @param attrs
     * @return
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
        list.clear();
        return FileVisitResult.CONTINUE;
/*
        System.out.println(file.toAbsolutePath());
        return super.visitFile(file, attrs);*/
    }
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

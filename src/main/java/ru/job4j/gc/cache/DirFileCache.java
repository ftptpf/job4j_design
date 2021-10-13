package ru.job4j.gc.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/** Конкретный класс создатель (реализация абстрактного класса создателя).
 * Программа считывает текстовые файлы из системы и выдает текст при запросе имени файла.
 * Если в кеше файла нет. Кеш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла.
 * Текстовые файлы лежать в одной директории. Пример. Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 */
public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Реализация фабричного метода.
     * @param key относительный путь к файлу в директории (например Names.txt)
     * @return содержимое файла (например содержимое Names.txt)
     */
    @Override
    protected String load(String key) {
        String result = "";
        Path path = Path.of(cachingDir, key);
        try {
            result = Files.lines(path)
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Читаем файл конфигурации.
 * path - путь к файлу "конфигурации" с которого берем данные
 * values -  хранит "ключ-значение"
 */
public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод считывает все ключи-значения в карту values.
     * Ключ-значения в исходном файле разделены символов равно.
     * Пустые строки и комментарии - пропускаем.
     */
    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            Predicate<String> predicateStartsWith = s -> !s.startsWith("#");
            values = in.lines()
                    .filter(predicateStartsWith)
                    .map(str -> str.split("="))
                    .filter(a -> a.length == 2)
                    .collect(Collectors.toMap(
                            s -> s[0],
                            s -> s[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает значение ключа.
     * @param key
     * @return
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}

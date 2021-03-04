package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;

/**
 * Читаем файл конфигурации.
 */
public class Config {
    private final String path; // Путь к файлу "конфигурации" с которого берем данные.
    private final Map<String, String> values = new HashMap<>(); // Хранит "ключ-значение".

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
            Predicate<String> predicateIsEmpty = s -> !s.isEmpty();
            in.lines()
                    .filter(predicateIsEmpty)
                    .filter(predicateStartsWith)
                    // .flatMap(s -> values.put(Arrays.stream(Arrays.stream(s.split(":")).findFirst().get(), Arrays.stream(Arrays.stream(s.split(":"))..get())
                    // .flatMap(s -> Arrays.stream(s.split(":")).map()).map();
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
        throw new UnsupportedOperationException("Don't impl this method yet!");
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


// Для считывания файлов нужно использовать
// import java.io.BufferedReader;
// import java.io.FileReader;
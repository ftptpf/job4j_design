package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Находим в файле и выводим на консоль все строки которые содержать число "404".
 */
public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            Predicate<String> predicate = s -> s.contains("404");
            list = in.lines().filter(predicate).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}

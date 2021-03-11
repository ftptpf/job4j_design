package ru.job4j.inout.io;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Анализ времени доступности сервера.
 */
public class Analizy {
    /**
     * Метод находит диапазоны времени, когда сервер не работал.
     * Сервер не работал, если status = 400 или 500.
     * Диапазон времени не работы сервера считается от стапуса 400-500 до статуса 200-300.
     * @param source имя лог-файла
     * @param target имя файла после анализа
     */
    int indicator = 0; // признак начала и окончания считывания периода работы сервера (0 - начинаем читать, 1 - заканчиваем)

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            Predicate<String> predicate = str -> {
                if ((str.startsWith("400") || str.startsWith("500")) && indicator == 0) {
                    indicator = 1;
                    return true;
                } else if ((str.startsWith("200") || str.startsWith("300")) && indicator == 1){
                    indicator = 0;
                    return true;
                }
                return false;
            };
            Predicate<String[]> predicateStringArray = arStr -> {
                if (arStr.)
            }


            in.lines()
                    .filter(predicate)
                    .map(str -> str.split(" "))
                    //.map(String::valueOf)
                    //.filter(predicateStringArray)
                    //.filter(a -> a.length() > 3)
                    //.map(a -> Integer.getInteger(a))
                    //.map(a -> Arrays.stream(a).skip(1))
                    //.skip(1)
                    //.collect(Collectors.toMap(a -> a[0], a -> a[1]))
                    .forEach(out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("resources/unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

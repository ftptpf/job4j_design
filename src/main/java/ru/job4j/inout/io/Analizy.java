package ru.job4j.inout.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * Анализ времени доступности сервера.
 */
public class Analizy {
    /**
     * Метод находит диапазоны времени, когда сервер не работал.
     * Сервер не работал, если status = 400 или 500.
     * @param source имя лог-файла
     * @param target имя файла после анализа
     */
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader("resources/serverlog.txt"))) {
            //in.readLine().startsWith(("400" || "500")) || in.readLine().
/*            in.lines()
                    .filter(a -> a.startsWith("400") || a.startsWith("500"))
                    .map(str -> str.split(" "))
                    .filter(a -> a.length == 2)
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));*/
            String[] array = (String[]) in.lines().toArray();
            for (String str : array) {
                str.startsWith("400") ||
            }
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

package ru.job4j.inout.io;

import java.io.*;

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
    private boolean indicator = true; // признак начала и окончания считывания периода работы сервера (true - начинаем читать, false - заканчиваем)
    private StringBuilder str = new StringBuilder();


    public void unavailable(String source) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines()
                    .forEach(line -> {
                        if ((line.startsWith("400") || line.startsWith("500")) && indicator == true) {
                            indicator = false;
                            str.append(line.substring(4)).append("; ");
                        }
                        if ((line.startsWith("200") || line.startsWith("300")) && indicator == false) {
                            indicator = true;
                            str.append(line.substring(4)).append(";").append(System.lineSeparator());
                        }
                            }
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String target) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            out.write(String.valueOf(str));

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

package ru.job4j.inout.io.encoding;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Чтение и запись информации в файл с учетом кодировки.
 */
public class UsageEncoding {
    /**
     * Метод чтения данных.
     * @param path
     * @return
     */
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            int data;
            while ((data = br.read()) > 0) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * Метод записи данных в файл.
     * @param path
     * @param data
     */
    public void writeDataInFile(String path, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            bw.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = ".\\resources\\text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        for (String str: strings) {
            encoding.writeDataInFile(path, str);
            
        }
        String s = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(s);
    }
}

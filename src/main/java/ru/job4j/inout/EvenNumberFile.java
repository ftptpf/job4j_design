package ru.job4j.inout;

import java.io.FileInputStream;
import java.util.Objects;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = fileInputStream.read()) != -1) {
                if (((char) read != '\r') && (((char) read) != '\n')) {
                    if ((char) read == '\r') {
                        continue;
                    }
                    if (((char) read) == '\n') {
                        continue;
                    }
                    text.append((char) read);
                    continue;
                }
                if (Integer.parseInt(text.toString()) % 2 == 0) {
                    System.out.println(text + " - четное число");
                    text.setLength(0);
                } else if (Integer.parseInt(text.toString()) % 2 != 0) {
                    System.out.println(text + " - нечетное число");
                    text.setLength(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

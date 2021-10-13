package ru.job4j.inout;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            do {
                read = fileInputStream.read();
                if (text.length() > 0 &&  (char) read == '\r' || read == -1) {
                    if (Integer.parseInt(text.toString()) % 2 == 0) {
                        System.out.println(text + " - четное число");
                        text.setLength(0);
                    } else if (Integer.parseInt(text.toString()) % 2 != 0) {
                        System.out.println(text + " - нечетное число");
                        text.setLength(0);
                    }
                }
                if ((char) read == '\r') {
                    continue;
                }
                if ((char) read == '\n') {
                    continue;
                }
                text.append((char) read);
            }
            while (read != -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

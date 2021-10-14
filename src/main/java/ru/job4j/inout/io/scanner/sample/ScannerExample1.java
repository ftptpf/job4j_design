package ru.job4j.inout.io.scanner.sample;

import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * Из потока данных необходимо вычленить только числа.
 * В качестве источника мы используем одну из реализаций Reader - CharArrayReader.
 * Данная реализация позволяет читать данные из массива символов, т.е. из временной памяти.
 * Шаблон разделителя не указан, поэтому используется тот, что по умолчанию,
 * а именно символы перехода на новую строку и пробел.
 */
public class ScannerExample1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 2 3",
                "4 5 6",
                "7 8 9"
        );
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
    }
}

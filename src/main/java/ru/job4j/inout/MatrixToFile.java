package ru.job4j.inout;

import java.io.FileOutputStream;

/**
 * Запись таблицы умножения в файл.
 */
public class MatrixToFile {
    public static void main(String[] args) {
        int size = 9; // размер массива
        int[][] table = new int[size][size]; // массив чисел
        String  nextLine = System.lineSeparator(); // разделитель для перехода на следующую строку
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    table[i][j] = (i + 1) * (j + 1);
                    out.write(Integer.toString(table[i][j]).getBytes()); // записываем значение
                    out.write(" ".getBytes()); // записываем пробел
                }
                out.write(nextLine.getBytes()); // переходим на новую строку
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

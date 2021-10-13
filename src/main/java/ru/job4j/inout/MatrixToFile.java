package ru.job4j.inout;

import java.io.FileOutputStream;

/**
 * Запись таблицы умножения в файл.
 * size - размер массива
 * table - массив чисел
 * nextLine - разделитель для перехода на следующую строку
 * out.write(Integer.toString(table[i][j]).getBytes()) - записываем значение
 * out.write(" ".getBytes()) - записываем пробел
 * out.write(nextLine.getBytes()) - переходим на новую строку
 */
public class MatrixToFile {
    public static void main(String[] args) {
        int size = 9;
        int[][] table = new int[size][size];
        String  nextLine = System.lineSeparator();
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    table[i][j] = (i + 1) * (j + 1);
                    out.write(Integer.toString(table[i][j]).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(nextLine.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

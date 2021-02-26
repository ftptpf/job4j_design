package ru.job4j.inout;

import java.io.FileOutputStream;
import java.util.Arrays;

public class MatrixToFile {
    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("matrix2.txt")) {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    out.write(table[i][j].);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
/*        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = (1 + i) * (1 + j);
            }
        }*/
        return table;
    }

    public static void main(String[] args) {
        multiple(9);
    }
/*
    public static void main(String[] args) {
        System.out.println(Arrays.toString(multiple(9)));
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            out.write(multiple(9).toString().getBytes());
            //out.write(multiple(9).toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

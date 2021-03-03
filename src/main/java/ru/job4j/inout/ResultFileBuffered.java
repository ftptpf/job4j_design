package ru.job4j.inout;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFileBuffered {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")
                )
        )) {
            out.write("Hello World!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFileBuffered {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

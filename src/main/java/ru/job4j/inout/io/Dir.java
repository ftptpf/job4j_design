package ru.job4j.inout.io;

import java.io.File;

/**
 * Получение всех элементов директории.
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) { // проверяем существование файла
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) { // проверяем что файл это директория
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subFile : file.listFiles()) { // получаем список файлов в этой директории
            System.out.println("Directory name: " + subFile.getName() + " ------  size: " + subFile.length());
        }
    }
}

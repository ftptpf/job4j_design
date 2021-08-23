package ru.job4j.gc.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс для работы с пользователем.
 * Позволяет пользователю:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 */
public class Emulator {
    static String cachingDir = "resources/gc"; // текущая директория
    private Set<String> fileSet = new HashSet<>(); // сет файлов текущей директории

    public void init(Scanner scanner, AbstractCache<String, String> fileCache) {
        loadToFileSet();
        boolean run = true;
        while (run) {
            showMenu();
            System.out.println("Current directory relative path is : " + cachingDir);
            System.out.println("Select menu number 0, 1, 2 or 3:");
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 0) { // выбираем кешируемую директорию
                System.out.println("Please chose directory:");
                cachingDir = scanner.nextLine();
                fileCache = new DirFileCache(cachingDir);
                loadToFileSet();
                System.out.println("You have a new directory.");
            } else if (select == 1) { // выбираем файл для загрузки в кеш
                System.out.println("For load file contents into cache please enter file name: ");
                String key = scanner.nextLine();
                if (fileSet.contains(key)) {
                    fileCache.load(key);
                    System.out.println("File content loaded into cache");
                } else {
                    System.out.println("Please type the correct file name.");
                }
            } else if (select == 2) { // получаем содержимое файла из кеша
                System.out.println("For get file contents from cache please enter file name:");
                String key = scanner.nextLine();
                if (fileSet.contains(key)) {
                    System.out.println("Below you can see file contents: ");
                    System.out.println(fileCache.get(key));
                } else {
                    System.out.println("Please type the correct file name.");
                }
            } else  if (select == 3) { // завершаем программу
                System.out.println("Program exit ...");
                run = false;
            } else {
                System.out.println("You chose the wrong menu number. Please try again.");
            }
        }
    }

    /**
     * Загружаем имена файлов директории.
     */
    private void loadToFileSet() {
        try {
            fileSet = Files.walk(Path.of(cachingDir), 1)
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вывод меню.
     */
    private void showMenu() {
        String[] menu = {"Select cached directory",
                "Load file contents into cache",
                "Get file contents from cache",
                "Exit Program"};
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractCache<String, String> fileCache = new DirFileCache(cachingDir);
        new Emulator().init(scanner, fileCache);
    }
}

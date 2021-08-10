package ru.job4j.gc.cache;

import java.util.Scanner;

/**
 * Класс для работы с пользователем.
 * Позволяет пользователю:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 */
public class Emulator {
    static String cachingDir = "resources/gc";

    public void init(Scanner scanner, AbstractCache<String, String> fileCache) {
        boolean run = true;
        while (run) {
            showMenu();
            System.out.println("Current directory relative path is : " + cachingDir);
            System.out.println("Select menu number: ");
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 0) {
                System.out.println("Please chose directory:");
                cachingDir = scanner.nextLine();
                fileCache = new DirFileCache(cachingDir);
                System.out.println("I have a new directory.");
            } else if (select == 1) {
                System.out.println("For load file contents into cache please enter file name: ");
                String key = scanner.nextLine();
                fileCache.load(key);
                System.out.println("File content loaded into cache");
            } else if (select == 2) {
                System.out.println("For get file contents from cache please enter file name:");
                String key = scanner.nextLine();
                System.out.println("Below you can see file contents: ");
                System.out.println(fileCache.get(key));
            } else  if (select == 3) {
                System.out.println("Program exit ...");
                run = false;
            } else {
                System.out.println("You chose the wrong menu number. Please try again.");
            }
        }
    }

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

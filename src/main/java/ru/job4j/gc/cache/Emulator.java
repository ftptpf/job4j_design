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
    public void init(Scanner scanner, AbstractCache<String, String> cache) {
        boolean run = true;
        while (run) {
            showMenu();
            System.out.println("Select menu number: ");
            int select = Integer.parseInt(scanner.nextLine());
            if (select != 3) {
                System.out.println("User chose: " + select);
            } else {
                run = false;
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
        AbstractCache<String, String> cache = new DirFileCache("/");
        new Emulator().init(scanner, cache);
    }
}

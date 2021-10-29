package ru.job4j.ood.isp.menu.input;

import java.util.Scanner;

/**
 * Получаем строковые данные от пользователя в консоли.
 */
public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}

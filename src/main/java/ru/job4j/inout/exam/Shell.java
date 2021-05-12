package ru.job4j.inout.exam;

import java.util.*;

public class Shell {
    private static String result;
    private List<String> list = new ArrayList<>();
    private static Deque<String> stack = new ArrayDeque<>();

    /**
     * Изменяет текущую директорию.
     * @param path
     */
    public void cd(String path) {
        if (path.startsWith("/")) {
            parseAbsolute(path);
        } else {
            parseRelative(path);
        }
    }

    /**
     * Выводим в терминале путь к текущей папке.
     * @return
     */
    public String pwd() {
        return result;
    }

    /**
     * Получаем абсолютный путь, разбиваем по / и заново все помещаем в стек,
     * предварительно его опустошая.
     * @param path
     */
    public static void parseAbsolute(String path) {
        String[] array = path.split("/"); // из абсолютного пути создаем массив директорий
        if (array.length == 0) { // если массив пуст
            result = "/"; // результату присваиваем корневую директорию
        } else {
            for (String dir: array) {
                if (!dir.isEmpty()) { // отфильтровываем пустые названия
                    stack.addFirst(dir); // размещаем названия директорий в начало стека
                }
            }
            StringBuilder sb = new StringBuilder("");
            while (!stack.isEmpty()) { // пока стек содержит данные
                sb.append("/").append(stack.removeLast()); // извлекаем из конца стека(очередь) имена директорий и собираем в абсолютный путь
            }
            result = sb.toString();
        }
    }

    /**
     * Получаем относительный путь (.. либо название директории).
     * Здесь уже манипулируете стеком.
     * @param path
     */
    public static void parseRelative(String path) {
    }
}

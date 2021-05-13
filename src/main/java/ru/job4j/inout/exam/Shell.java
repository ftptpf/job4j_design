package ru.job4j.inout.exam;

import java.nio.file.Path;
import java.util.*;

/**
 * Симуляция работы в терминале Linux команд "cd" и "pwd".
 */
public class Shell {
    private String result;
    // private List<String> list = new ArrayList<>();
    private Deque<String> queue = new ArrayDeque<>();

    /**
     * Симуляция работы команды смены диретории.
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
     * Симуляция работы вывода абсолютного пути текущей директории.
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
    public void parseAbsolute(String path) {
        String[] array = path.split("/"); // из абсолютного пути создаем массив директорий
        if (array.length == 0) { // если массив пуст
            result = "/"; // результату присваиваем корневую директорию
        } else {
        addToQueue(array);
        takeFromQueue();
        }
    }

    /**
     * Получаем относительный путь (.. либо название директории).
     * Здесь уже манипулируете стеком.
     * @param path
     */
    public void parseRelative(String path) {
        String[] array = path.split("/"); // из абсолютного пути создаем массив директорий
        if (array.length > 0 && array[0].equals("..")) {
            // array[0] = "";
            String[] oldArray;
            if (!result.isEmpty()) {
                oldArray = result.split("/");
            } else {
                oldArray = new String[]{""};
            }

            addToQueue(oldArray);
            queue.removeLast();
            addToQueue(array);
            queue.removeLast();
            takeFromQueue();
        }
    }

    public void addToQueue(String[] array) {
        for (String dir: array) {
            if (!dir.isEmpty()) { // отфильтровываем пустые названия
                queue.addFirst(dir); // размещаем названия директорий в начало очереди
            }
        }
    }

    public String takeFromQueue() {
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) { // пока стек содержит данные
            sb.append("/").append(queue.removeLast()); // извлекаем из конца очереди имена директорий и собираем в абсолютный путь
        }
        return result = sb.toString();
    }



}

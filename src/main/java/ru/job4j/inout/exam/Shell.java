package ru.job4j.inout.exam;

import java.util.*;

/**
 * Симуляция работы в терминале Linux команд "cd" и "pwd".
 */
public class Shell {
    private String result = "";
    private Deque<String> queue = new ArrayDeque<>(); // очередь в которую будут собираться и извлекаться наименования директорий

    /**
     * Симуляция работы команды смены директории.
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
     * Работаем с даными абсолютного пути.
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
     * Работаем с данного относительного пути.
     * @param path
     */
    public void parseRelative(String path) {
        String[] array = path.split("/"); // из нового абсолютного пути создаем массив директорий
        String[] oldArray = result.split("/"); // из текущего абсолютного пути создаем массив директорий
        if (array.length > 0 && !array[0].equals("..")) {
            addToQueue(oldArray);
            addToQueue(array);
        } else {
            addToQueue(oldArray); // добавляем в очередь текущий массив
            queue.removeFirst(); // из него убираем первое значение, т.е. перемещаемся в родительскую директорию
            addToQueue(array); // добавляем новый массив директорий
            queue.removeLast(); // убираем последнее значение, т.е. перемещаемся во вложенную папку
        }
        takeFromQueue();

    }

    /**
     * Добавляем массив директорий в очередь.
     * @param array
     */
    public void addToQueue(String[] array) {
        for (String dir: array) {
            if (!dir.isEmpty()) { // отфильтровываем пустые названия
                queue.addFirst(dir); // размещаем названия директорий в начало очереди
            }
        }
    }

    /**
     * Извлекаем из очереди абсолютный путь.
     * @return
     */
    public String takeFromQueue() {
        StringBuilder sb = new StringBuilder();
        if (queue.isEmpty()) { // если очередь пуста
            sb = sb.append("/"); // присваиваем значение корневой директории
        }
        while (!queue.isEmpty()) { // пока очередь содержит данные
            sb.append("/").append(queue.removeLast()); // извлекаем из конца очереди имена директорий и собираем в абсолютный путь
        }
        return result = sb.toString();
    }
}

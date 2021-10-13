package ru.job4j.inout.exam;

import java.util.*;

/**
 * Симуляция работы в терминале Linux команд "cd" и "pwd".
 * queue - очередь в которую будут собираться и извлекаться наименования директорий
 */
public class Shell {
    private String result = "";
    private Deque<String> queue = new ArrayDeque<>();

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
     * Работаем с данными абсолютного пути.
     * Из абсолютного пути создаем массив директорий. Если массив пуст - результату присваиваем корневую директорию.
     * @param path
     */
    public void parseAbsolute(String path) {
        String[] array = path.split("/");
        if (array.length == 0) {
            result = "/";
        } else {
        addToQueue(array);
        takeFromQueue();
        }
    }

    /**
     * Работаем с данного относительного пути.
     * Из нового абсолютного пути создаем массив директорий.
     * Из текущего абсолютного пути создаем массив директорий.
     * Добавляем в очередь текущий массив - из него убираем первое значение, т.е. перемещаемся в родительскую директорию.
     * Добавляем новый массив директорий - убираем последнее значение, т.е. перемещаемся во вложенную папку.
     * @param path
     */
    public void parseRelative(String path) {
        String[] array = path.split("/");
        String[] oldArray = result.split("/");
        if (array.length > 0 && !array[0].equals("..")) {
            addToQueue(oldArray);
            addToQueue(array);
        } else {
            addToQueue(oldArray);
            queue.removeFirst();
            addToQueue(array);
            queue.removeLast();
        }
        takeFromQueue();
    }

    /**
     * Добавляем массив директорий в очередь.
     * Отфильтровываем пустые названия. Размещаем названия директорий в начало очереди.
     * @param array
     */
    public void addToQueue(String[] array) {
        for (String dir: array) {
            if (!dir.isEmpty()) {
                queue.addFirst(dir);
            }
        }
    }

    /**
     * Извлекаем из очереди абсолютный путь.
     * Если очередь пуста - присваиваем значение корневой директории.
     * Пока очередь содержит данные - извлекаем из конца очереди имена директорий и собираем в абсолютный путь.
     * @return
     */
    public String takeFromQueue() {
        StringBuilder sb = new StringBuilder();
        if (queue.isEmpty()) {
            sb = sb.append("/");
        }
        while (!queue.isEmpty()) {
            sb.append("/").append(queue.removeLast());
        }
        return result = sb.toString();
    }
}

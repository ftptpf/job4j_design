package ru.job4j.control1;

import java.util.*;

/**
 * Класс определения разницы между начальным состояние массива и измененным.
 */
public class Analize {
    /**
     * Метод возвращает статистику об изменении коллекции.
     * @param previous начальные данные.
     * @param current измененные данные.
     * @return
     */
    public Info diff(List<User> previous, List<User> current) {
        int add = 0; // Счетчик добавленных элементов.
        int change = 0; // Счетчик измененных элементов.
        int delete = 0; // Счетчик удаленных элементов.

        Map<Integer, String>  curMap = new HashMap<>(); // создаем map
        for (User cUser : current) { // в него переносим все элементы current списка
            curMap.put(cUser.getId(), cUser.getName());
        }

        for (User prUser : previous) { // берем элемент исходного списка previous
            // так как результат удаления по индексу можем быть null либо String (имя пользователя) - заворачиваем все в Optional
            Optional<String> check = Optional.ofNullable(curMap.remove(prUser.getId()));
            if (check.isEmpty()) { // если возвращается пустое значение - ранее элемент уже был удален
                delete++; // увеличиваем счетчик уделенных элементов
            } else if (!check.get().equals(prUser.getName())) { // если возвращаемое значение (имя пользователя) отличается в начальном и конечном списках
                change++; // увеличиваем счетчик измененных элементов
            }
        }
        add = curMap.size(); // так как мы удалили все элементы map по индексам начального списка, в map останутся только добавленные элементы
        return new Info(add, change, delete);
    }
}

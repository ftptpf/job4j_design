package ru.job4j.control1;

import java.util.*;

/**
 * Класс определения разницы между начальным состояние массива и измененным.
 */
public class Analize {
    /**
     * Метод возвращает статистику об изменении коллекции.
     * add - счетчик добавленных элементов
     * change - счетчик измененных элементов
     * delete - счетчик удаленных элементов
     * Создаем map, в него переносим все элементы current списка.
     * Берем элемент исходного списка previous.
     * Так как результат удаления по индексу можем быть null либо String (имя пользователя) - заворачиваем все в Optional.
     * Если возвращается пустое значение - ранее элемент уже был удален.  Увеличиваем счетчик уделенных элементов.
     * сли возвращаемое значение (имя пользователя) отличается в начальном и конечном списках -
     * увеличиваем счетчик измененных элементов.
     * Так как мы удалили все элементы map по индексам начального списка, в map останутся только добавленные элементы.
     *
     * @param previous начальные данные.
     * @param current измененные данные.
     * @return
     */
    public Info diff(List<User> previous, List<User> current) {
        int add = 0;
        int change = 0;
        int delete = 0;

        Map<Integer, String>  curMap = new HashMap<>();
        for (User cUser : current) {
            curMap.put(cUser.getId(), cUser.getName());
        }
        for (User prUser : previous) {
            Optional<String> check = Optional.ofNullable(curMap.remove(prUser.getId()));
            if (check.isEmpty()) {
                delete++;
            } else if (!check.get().equals(prUser.getName())) {
                change++;
            }
        }
        add = curMap.size();
        return new Info(add, change, delete);
    }
}

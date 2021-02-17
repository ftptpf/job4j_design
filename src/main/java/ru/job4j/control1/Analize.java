package ru.job4j.control1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

       Set<Integer> idSet = new HashSet<>(); // Общий сет всех уникальных id двух списков previous и current.

        for (User cUser : current) { // Берем элемент из списка измененных данных
            if (!previous.contains(cUser)) { // Если его небыло в списке начальных данных
                add++; // Увеличиваем счетчик добавленных элементов.
            }
            idSet.add(cUser.getId()); // Собираем в сет id списка current.
        }

        for (User prUser : previous) { // Берем элемент из списка начальных данных
            if (!current.contains(prUser)) { // Если его нет в списке измененных данных
                delete++; // Увеличиваем счетчик удаленных элементов.
            }
            idSet.add(prUser.getId()); // Собираем в сет id списка previous.
        }

        for (int id : idSet) {
            for (User prUser : previous) {
                for (User cUser : current) {
                    if (id == prUser.getId() && id == cUser.getId() && !prUser.getName().equals(cUser.getName())) {
                        change++; // Увеличиваем счетчик измененных элементов.
                    }
                }
            }
        }

        return new Info(add, change, delete);
    }
}

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
        for (User idUser : previous) { // Собираем в сет id списка previous.
            idSet.add(idUser.getId());
        }
        for (User idUser : current) { // Собираем в сет id списка current.
            idSet.add(idUser.getId());
        }

/*        for (int id : idSet) {
            for (User prUser : previous) {
                for (User cUser : current) {
                    if (id == prUser.getId() && id == cUser.getId() && !prUser.getName().equals(cUser.getName())) {
                        change++; // Увеличиваем счетчик измененных элементов.
                    } else if (!current.contains(prUser)) {
                        delete++; // Увеличиваем счетчик удаленных элементов.
                    } else if (!previous.contains(cUser)) {
                        add++; // Увеличиваем счетчик добавленных элементов.
                    }
                }
            }
        }*/

        int p = previous.size();
        for (int index = 0; index < p; index++) {
            if (!current.contains(previous.get(index))) {
                delete++;
                previous.remove(index);
            }
        }
        for (User cUser : current) {
            if (!previous.contains(cUser)) {
                add++;
            }
        }



        return new Info(add, change, delete);

    }
}

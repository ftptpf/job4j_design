package ru.job4j.control1;

import java.util.List;

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
        int add = 0;
        int change = 0;
        int delete = 0;
        // Info rsl; // ссылка на объект Info в котором будех собрана информация о изменении данных в коллекции.
        for (User previousUser : previous) {
            for (User currentUser : current) {
                if (previousUser.equals(currentUser)) {
                    previous.remove(previousUser);
                    current.remove(currentUser);
                }
            }
        }


        return new Info(add, change, delete);

    }
}

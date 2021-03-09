package ru.job4j.control1;

import java.util.*;

/**
 * Реализация слияния информации о пользователях.
 * Если у двух пользователей есть общий email, значит это один и тот же пользователь.
 * Список объединенного пользователя содержит только уникальные email-ы.
 */
public class UserEmails {

    public Map<String, Set<String>> merge(Map<String, Set<String>> users) {
        Map<String, Set<String>> result = new HashMap<>(users); // создаем файл итоговых данных помещая в него исходные данные
        for (Map.Entry<String, Set<String>> userOne : users.entrySet()) { // берем пользователя из map
            String nameOneUser = userOne.getKey(); // имя пользователя
            Set<String> emailsOneUsers = userOne.getValue(); // set с email-ми

            for (Map.Entry<String, Set<String>> otherUser : users.entrySet()) { // берем пользователя из map
                String nameOtherUser = otherUser.getKey(); // имя пользователя
                Set<String> emailsOtherUser = otherUser.getValue(); // set с email-ми
                if (nameOneUser.equals(nameOtherUser)) { // если два имени равные
                    continue; // прерываем внутренний цикл и берем следующего пользователя nameOtherUser
                }
                if (!result.containsKey(nameOneUser)) { // если nameOtherUser нет среди ключей итогового файла (был проверен и удален)
                    continue; // прерываем внутренний цикл и берем следующего пользователя nameOtherUser
                }

                if (!Collections.disjoint(emailsOneUsers, emailsOtherUser)) { // если у двух set-ов есть хотя бы один общий email
                    result.get(nameOneUser).addAll(emailsOtherUser); // к set-у email-ов в итоговом файле дописываем emailsOtherUser
                    result.put(nameOneUser, emailsOneUsers); // перезаписываем map в итоговом файле
                    result.remove(nameOtherUser); // удаляем в итоговом файле map в котором было совпадение email
                }
            }
        }
        return result;
    }
}

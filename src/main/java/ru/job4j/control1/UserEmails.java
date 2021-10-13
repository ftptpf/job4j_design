package ru.job4j.control1;

import java.util.*;

/**
 * Реализация слияния информации о пользователях.
 * Если у двух пользователей есть общий email, значит это один и тот же пользователь.
 * Список объединенного пользователя содержит только уникальные email-ы.
 */
public class UserEmails {
    /**
     * Создаем файл итоговых данных помещая в него map исходных данных, далее будет происходить редактирование этого файла.
     * Берем пользователя из map - имя основного пользователя - set с email-ми.
     * Берем пользователя из map - имя "вспомогательного" пользователя которого сравниваем с основным - set с email-ми.
     * Если имена основного и "вспомогательного" пользователя равны -
     * прерываем внутренний цикл и берем следующего "вспомогательного" пользователя nameOtherUser.
     * Если nameOtherUser нет среди ключей итогового файла (был уже проверен и удален) -
     * прерываем внутренний цикл и берем следующего "вспомогательного" пользователя nameOtherUser.
     * Если у двух set-ов есть хотя бы один общий email -
     * к set-у email-ов основного пользователя добавляем set email-ов "вспомогательного" пользователя emailsOtherUser.
     * Перезаписываем map в итоговом файле, удаляем в итоговом файле map в котором находился "вспомогательный" пользователь,
     * в set-е которого было совпадение email c основным.
     * @param users
     * @return
     */
    public Map<String, Set<String>> merge(Map<String, Set<String>> users) {
        Map<String, Set<String>> result = new HashMap<>(users);

        for (Map.Entry<String, Set<String>> userOne : users.entrySet()) {
            String nameOneUser = userOne.getKey();
            Set<String> emailsOneUsers = userOne.getValue();
            for (Map.Entry<String, Set<String>> otherUser : users.entrySet()) {
                String nameOtherUser = otherUser.getKey();
                Set<String> emailsOtherUser = otherUser.getValue();
                if (nameOneUser.equals(nameOtherUser)) {
                    continue;
                }
                if (!result.containsKey(nameOneUser)) {
                    continue;
                }
                if (!Collections.disjoint(emailsOneUsers, emailsOtherUser)) {
                    emailsOneUsers.addAll(emailsOtherUser);
                    result.put(nameOneUser, emailsOneUsers);
                    result.remove(nameOtherUser);
                }
            }
        }
        return result;
    }
}

package ru.job4j.control1;

import java.util.*;

/**
 * Реализация слияния информции о пользователех.
 * Если у двух пользователей есть общий email, значит это один и тот же пользователь.
 * Список объединенного пользователя содержит только уникальные email-ы.
 */
public class UserEmails {
    public List<UserData> mergeUser(List<UserData> userDataInfo) {
        List<UserData> result = new ArrayList<>();
        for (int i = 0; i < userDataInfo.size(); i++) {

        }


        return result;
    }
    public Map<String, Set<String>> merge(Map<String, Set<String>> users) {
        Map<String, Set<String>> result = new HashMap<>(users); // итоговые данные
        //Map<String, Set<String>> tempMap = new HashMap<>(); // промежуточные данные
        for (Map.Entry<String, Set<String>> user : users.entrySet()) {
            String nameInputUser = user.getKey();
            Set<String> emailsInputUsers = user.getValue();
            for (Map.Entry<String, Set<String>> resultUser : result.entrySet()) {
                String nameResultUser = resultUser.getKey();
                Set<String> emailsResultUser = resultUser.getValue();
                if (nameResultUser.equals(nameInputUser)) {
                    users.remove(nameInputUser);
                    continue;
                }
                if (!Collections.disjoint(emailsResultUser, emailsInputUsers)) {
                    emailsResultUser.addAll(emailsInputUsers);
                    result.put(nameResultUser, emailsResultUser);
                    users.remove(nameInputUser);
                }
                 {

                }
            }
/*            if (result.isEmpty()) {
                result.put(user.getKey(), user.getValue());
                users.remove(user.getKey());
                continue;
            }
            //for ()*/
        }


/*        Collections.disjoint()
        //Set<String> tempSet = new HashSet<>();
        for (Map.Entry<String, Set<String>> user : users.entrySet()) { // поочередно перебираем данные каждого пользователя
            String currentUser = user.getKey();
            Set<String> currentEmailsSet = new HashSet<>(user.getValue()); // временный Set
            for (String email : user.getValue()) { // берем первый email из списка
                tempSet.add(user.getKey()); // во временный Set добавляем пользователя
                tempMap.put(email, tempSet.add(user.getKey())); // добавдяем во временный Map где ключь - email, а значение Set пользователей
            }
            //tempSet.clear();
*//*            if (result.isEmpty()) {
                result.put(user.getKey(), user.getValue());
            }*//*
        }
        for (Map.Entry<String, Set<String>> user : tempMap.entrySet()) {
            Set<String> tempSet = new HashSet<>();
            for (String name : user.getValue()) {
                tempSet.add(user.getKey());
                result.put(user.getValue().stream().findFirst().get(), tempSet);
            }
        }*/

        return result;
    }
/*    public Map<String, Set<Email>> mergeUser(Map<String, Set<Email>> userEmailsInfo) {
        Map<String, Set<Email>> resultDataMap = new HashMap<>();
        Map<Email, String> reversResultDataMap = new HashMap<>();
        for (Map.Entry<String, Set<Email>> user : userEmailsInfo.entrySet()) {
            String name = user.getKey();
            Set<Email> emails = user.getValue();
            for (Email email : emails) {
                reversResultDataMap.put(email, name);
            }
        }
        return resultDataMap;
    }*/
/*    public List<UserData> mergerUser(List<UserData> usersArrayList) {

        for (UserData userData : usersArrayList) {

            usersArrayList.remove(userData);
        }

        Map<String, UserData> userDataMap = new HashMap<>();
        for (int i = 1; i < usersArrayList.size(); i++) {
            UserData uData1 = usersArrayList.get(i - 1);
            UserData uData2 = usersArrayList.get(i);
            List<String> lStr1 = uData1.getEmailList();
            List<String> lStr2 = uData2.getEmailList();
            if (lStr1.retainAll(lStr2)) {

            }
        }

        List<UserData> resultList = new ArrayList<>();

        return resultList;
    }*/
}

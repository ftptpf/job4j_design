package ru.job4j.control1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserEmails {
    public List<UserData> mergerUser(List<UserData> usersArrayList) {

        for (UserData userData : usersArrayList) {

            usersArrayList.remove(userData);
        }

        Map<String,UserData> userDataMap = new HashMap<>();
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
    }
}

package ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserMap {
    public static void main(String[] args) {
        User userOne = new User("Nik", 3, new GregorianCalendar(2000, 01, 30));
        User userTwo = new User("Nik", 3, new GregorianCalendar(2000, 01, 30));
        Map<User, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(userOne, userOne.name);
        userObjectMap.put(userTwo, userTwo.name);
        System.out.println(userObjectMap);
        System.out.println(userOne);
        System.out.println(userTwo);
        System.out.println(userOne.equals(userTwo));
        System.out.println(userOne == userTwo);
    }
}

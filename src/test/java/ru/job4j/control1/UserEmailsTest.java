package ru.job4j.control1;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


public class UserEmailsTest {

        @Test
    public void mergerUser() {
        Map<String, Set<String>> users = new HashMap<>();
        Set<String> usersSet1 = new HashSet<>();
        usersSet1.add("xxx@ya.ru");
        usersSet1.add("foo@gmail.com");
        usersSet1.add("lol@mail.ru");
        users.put("user1", usersSet1);

        Set<String> userSet2 = new HashSet<>();
        userSet2.add("foo@gmail.com");
        userSet2.add("ups@pisem.net");
        users.put("user2", userSet2);

        Set<String> userSet3 = new HashSet<>();
        userSet3.add("xyz@pisem.net");
        userSet3.add("vasya@pupkin.com");
        users.put("user3", userSet3);

        Set<String> userSet4 = new HashSet<>();
        userSet4.add("ups@pisem.net");
        userSet4.add("aaa@bbb.ru");
        users.put("user4", userSet4);

        Set<String> userSet5 = new HashSet<>();
        userSet5.add("xyz@pisem.net");
        users.put("user5", userSet5);


        Map<String, Set<String>> afterMergerUser = new HashMap<>();
        afterMergerUser.put("user1", Set.of("aaa@bbb.ru", "ups@pisem.net", "lol@mail.ru", "xxx@ya.ru", "foo@gmail.com"));
        afterMergerUser.put("user5", Set.of("vasya@pupkin.com", "xyz@pisem.net"));

        UserEmails uEmails = new UserEmails();
        Map<String, Set<String>> mrUsers = uEmails.merge(users);
        assertThat(mrUsers, is(afterMergerUser));
    }
}

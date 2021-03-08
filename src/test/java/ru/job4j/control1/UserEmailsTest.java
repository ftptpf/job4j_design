package ru.job4j.control1;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


public class UserEmailsTest {

        @Test
    public void mergerUser() {
        Map<String, Set<String>> users = new HashMap<>();
        users.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        users.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        users.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        users.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        users.put("user5", Set.of("xyz@pisem.net"));

        Map<String, Set<String>> afterMergerUser = new HashMap<>();
        afterMergerUser.put("user1",Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"));
        afterMergerUser.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));

        UserEmails uEmails = new UserEmails();
        Map<String, Set<String>> mrUsers = uEmails.merge(users);
        assertThat(mrUsers, is(afterMergerUser));
    }

/*    @Test
    public void mergerUser() {
        List<UserData> users = new ArrayList<>();
        users.add(new UserData("user1", Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        users.add(new UserData("user2", Arrays.asList("foo@gmail.com", "ups@pisem.net")));
        users.add(new UserData("user3", Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        users.add(new UserData("user4", Arrays.asList("ups@pisem.net", "aaa@bbb.ru")));
        users.add(new UserData("user5", Arrays.asList("xyz@pisem.net")));

        UserEmails userEmails = new UserEmails();
        //userEmails.mergerUser(users);

        List<UserData> mergerUsers = new ArrayList<>();
        mergerUsers.add(new UserData("user1", Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru")));
        mergerUsers.add(new UserData("user3", Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));

        assertThat(userEmails, is(mergerUsers));
    }*/
}

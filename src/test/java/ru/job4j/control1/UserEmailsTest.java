package ru.job4j.control1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


public class UserEmailsTest {

    @Test
    public void mergerUser() {
        List<UserData> users = new ArrayList<>();
        users.add(new UserData("user1", Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        users.add(new UserData("user2", Arrays.asList("foo@gmail.com", "ups@pisem.net")));
        users.add(new UserData("user3", Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        users.add(new UserData("user4", Arrays.asList("ups@pisem.net", "aaa@bbb.ru")));
        users.add(new UserData("user5", Arrays.asList("xyz@pisem.net")));

        UserEmails userEmails = new UserEmails();
        userEmails.mergerUser(users);

        List<UserData> mergerUsers = new ArrayList<>();
        mergerUsers.add(new UserData("user1", Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru")));
        mergerUsers.add(new UserData("user3", Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));

        assertThat(userEmails, is(mergerUsers));
    }
}

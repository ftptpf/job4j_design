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
        List<Users> users = new ArrayList<>();
        users.add(new Users("user1", Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        users.add(new Users("user2", Arrays.asList("foo@gmail.com", "ups@pisem.net")));
        users.add(new Users("user3", Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        users.add(new Users("user4", Arrays.asList("ups@pisem.net", "aaa@bbb.ru")));
        users.add(new Users("user5", Arrays.asList("xyz@pisem.net")));

        UserEmails userEmails = new UserEmails();
        userEmails.mergerUser(users);

        List<Users> mergerUsers = new ArrayList<>();
        mergerUsers.add(new Users("user1", Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru")));
        mergerUsers.add(new Users("user3", Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));

        assertThat(userEmails, is(mergerUsers));
    }
}

package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void addUserFindByIdTest() {
        Store<User> userStore = new MemStore<>();
        userStore.add(new User("a", "Olga"));
        userStore.add(new User("b", "Gala"));
        assertThat(userStore.findById("a").getName(), is("Olga"));
        assertThat(userStore.findById("b").getName(), is("Gala"));
    }

    @Test
    public void replaceUserFindByIdTest() {
        Store<User> userStore = new MemStore<>();
        userStore.add(new User("a", "Olga"));
        userStore.add(new User("b", "Gala"));
        assertThat(userStore.findById("a").getName(), is("Olga"));
        assertThat(userStore.findById("b").getName(), is("Gala"));
        userStore.replace("a", new User("a", "Nina"));
        assertThat(userStore.findById("a").getName(), is("Nina"));
    }

    @Test
    public void deleteUserFindByIdTest() {
        Store<User> userStore = new MemStore<>();
        userStore.add(new User("a", "Olga"));
        userStore.add(new User("b", "Gala"));
        assertThat(userStore.findById("a").getName(), is("Olga"));
        assertThat(userStore.findById("b").getName(), is("Gala"));
        userStore.delete("a");
        assertNull(userStore.findById("a"));
    }
}
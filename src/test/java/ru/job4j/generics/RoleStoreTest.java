package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void addRoleFindByIdTest() {
        Store<Role> roleStore = new MemStore<>();
        roleStore.add(new Role("z", "One"));
        roleStore.add(new Role("y", "Two"));
        assertThat(roleStore.findById("z").getType(), is("One"));
        assertThat(roleStore.findById("y").getType(), is("Two"));
    }

    @Test
    public void replaceRoleFindByIdTest() {
        Store<Role> roleStore = new MemStore<>();
        roleStore.add(new Role("z", "One"));
        roleStore.add(new Role("y", "Two"));
        assertThat(roleStore.findById("z").getType(), is("One"));
        assertThat(roleStore.findById("y").getType(), is("Two"));
        roleStore.replace("z", new Role("z", "Three"));
        assertThat(roleStore.findById("z").getType(), is("Three"));
    }

    @Test
    public void deleteRoleFindByIdTest() {
        Store<Role> roleStore = new MemStore<>();
        roleStore.add(new Role("z", "One"));
        roleStore.add(new Role("y", "Two"));
        assertThat(roleStore.findById("z").getType(), is("One"));
        assertThat(roleStore.findById("y").getType(), is("Two"));
        roleStore.delete("z");
        assertNull(roleStore.findById("z"));
    }
}
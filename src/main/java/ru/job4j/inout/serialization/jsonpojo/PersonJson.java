package ru.job4j.inout.serialization.jsonpojo;

import java.util.Arrays;

public class PersonJson {
    private final boolean sex;
    private final int age;
    private final ContactJson contact;
    private final String[] statuses;

    public PersonJson(boolean sex, int age, ContactJson contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public ContactJson getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}

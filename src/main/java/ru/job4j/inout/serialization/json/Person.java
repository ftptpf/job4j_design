package ru.job4j.inout.serialization.json;

import java.util.Arrays;

public class Person {
    private final boolean sex;
    private final int age;
    private final ContactSimple contact;
    private final String[] statuses;

    public Person(boolean sex, int age, ContactSimple contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
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

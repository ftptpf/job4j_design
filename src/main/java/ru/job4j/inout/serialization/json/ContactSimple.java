package ru.job4j.inout.serialization.json;

public class ContactSimple {
    private final String phone;

    public ContactSimple(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactSimple{"
                + "phone='" + phone
                + '\'' + '}';
    }
}

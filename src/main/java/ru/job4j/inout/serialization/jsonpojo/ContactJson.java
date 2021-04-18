package ru.job4j.inout.serialization.jsonpojo;

public class ContactJson {
    private final String phone;

    public ContactJson(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "ContactSimple{"
                + "phone='" + phone
                + '\'' + '}';
    }
}

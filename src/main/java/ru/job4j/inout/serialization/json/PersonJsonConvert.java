package ru.job4j.inout.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersonJsonConvert {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new ContactSimple("11-111"), "Worker", "Married");
        /* Преобразуем объет Person в json строку */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}

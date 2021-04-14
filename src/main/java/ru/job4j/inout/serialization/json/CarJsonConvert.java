package ru.job4j.inout.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CarJsonConvert {
    public static void main(String[] args) {
        final Car car = new Car("VW",1990, true, new Engine(100), "Ivan", "Sergey");
        /* Преобразуем объет Person в json строку */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /* Модифицируем json строку */
        final String carBwm =
                "{"
                        + "\"brand\":BWM,"
                        + "\"year\":2005,"
                        + "\"gas\":true,"
                        + "\"engine\":"
                        + "{"
                        + "\"power\":\"150\""
                        + "},"
                        + "\"owners\":"
                        + "[\"Igor\",\"Gena\"]"
                        + "}";
        final Car bwmCar = gson.fromJson(carBwm, Car.class);
        System.out.println(bwmCar);
    }
}

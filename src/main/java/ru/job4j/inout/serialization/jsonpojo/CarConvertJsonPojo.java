package ru.job4j.inout.serialization.jsonpojo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarConvertJsonPojo {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"power\":\"250\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Igor");
        list.add("Gena");
        JSONArray jsonOwner = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final CarJsonPojo car = new CarJsonPojo("BWM", 2009, true, new EngineJsonPojo(150), "Inna", "Nika");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("brand", car.getBrand());
        jsonObject.put("year", car.getYear());
        jsonObject.put("gas", car.isGas());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("owners", jsonOwner);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}

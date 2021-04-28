package ru.job4j.inout.control;

import java.util.HashMap;
import java.util.Map;

/**
 * Принимаем массив параметров и разбиваем их на пары: ключ, значение.
 */
public class ParamNames {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Check the source data. It's empty.");
        }
        for (String str : args) { // берем строку из полученного массива строк
            String[] keyAndParamArray = str.split("=", 2); // делим на две части по знаку "=", получаем новый массив строк
            if (validate(keyAndParamArray)) {
                throw new IllegalArgumentException("Check the source data. Wrong keys and parameters in it.");
            }
            values.put(keyAndParamArray[0].substring(1), keyAndParamArray[1]);
        }
    }
    public static boolean validate(String[] array) {
        return array.length != 2 || array[0].isEmpty() || array[1].isEmpty();
    }

    public static ParamNames of(String[] args) {
        ParamNames name = new ParamNames();
        name.parse(args);
        return name;
    }
}

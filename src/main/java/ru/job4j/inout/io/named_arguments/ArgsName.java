package ru.job4j.inout.io.named_arguments;

import java.util.HashMap;
import java.util.Map;

/**
 * Принимаем массив параметров и разбиваем их на пары: ключ, значение.
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        /* TODO parse args to values. */
        if (args.length < 1) {
            throw new IllegalArgumentException("Check the source data. It's empty.");
        }
        for (String str : args) { // берем строку из полученного массива строк
            String[] keyAndParamArray = str.split("=", 2); // делим на две части по знаку "=", получаем новый массив строк
            if (validate(keyAndParamArray)) {
                throw new IllegalArgumentException("Check the source data. Check the source data. Wrong keys and parameters in it.");
            }
            values.put(keyAndParamArray[0].substring(1),keyAndParamArray[1]);
        }
    }
    public static boolean validate(String[] array) {
        return array.length != 2 || array[0].isEmpty() || array[1].isEmpty();
    }

    public static ArgsName of(String[] args) {
        ArgsName name = new ArgsName();
        name.parse(args);
        return name;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

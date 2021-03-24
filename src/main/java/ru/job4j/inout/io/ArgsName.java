package ru.job4j.inout.io;

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
            if (keyAndParamArray[0].startsWith("-")) { // если правая часть начинается со знака "-"
                char[] chars = keyAndParamArray[0].toCharArray(); // переводим её в массив символов
                char[] result = new char[chars.length - 1]; // создаем новый массив символов куда запишем результат, размер уменьшам на 1
                int index = 0; // задаем индекс по которому у нас находится символ "-"
                System.arraycopy(chars, index + 1, result, index, chars.length - index -1); // копируем старый массив в новый со смещением вправо отбрасывая "-"
                keyAndParamArray[0] = new String(result); // перезаписываем значение преобразуя новый массив символов в строку
            }

            if (keyAndParamArray.length != 2 || keyAndParamArray[0].isEmpty() || keyAndParamArray[1].isEmpty()) {
                throw new IllegalArgumentException("Check the source data. Wrong keys and parameters in it.");
            }
            values.put(keyAndParamArray[0],keyAndParamArray[1]);
        }
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

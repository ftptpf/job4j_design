package ru.job4j.inout.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        for (String str : args) {
            Map<String, String> map = Arrays.stream(str.split("=", 2))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
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

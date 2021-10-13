package ru.job4j.gc.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Пример безопасной работы с SoftReference и WeakReference.
 * Размер heap установлен 4мБ с помощью ключей -Xmx4m -Xms4m
 */
public class SoftWeakReference {
    public static void main(String[] args) {
        exampleSafeSoft();
        exampleSafeWeak();
        /** exampleSoft(); */
        /** exampleWeak(); */
    }

    /**
     * Пример безопасной работы с Soft Reference.
     * Создаем два list-а Soft Reference <String>.
     * В первом листе добавляем объекты в количестве i.
     * Из первого list-а извлекаем String оборачиваем в Soft Reference
     * и добавляем во второй list.
     * Выводим статистику переброшенных объектов.
     */
    private static void exampleSafeSoft() {
        List<SoftReference<String>> softList = new ArrayList<>();
        List<SoftReference<String>> notNullSoftList = new ArrayList<>();
        for (int i = 0; i < 30_000; i++) {
            softList.add(new SoftReference<>("Type_" + i));
        }
        System.out.println(" --- Soft Reference --- ");
        System.out.println("Size soft list: " + softList.size());

        int countNullString = 0;

        for (SoftReference<String> strWeak : softList) {
            String str = strWeak.get();
            if (str != null) {
                notNullSoftList.add(new SoftReference<>(str));
            } else {
                countNullString++;
            }
        }
        System.out.println("Size not null String list: " + notNullSoftList.size());
        System.out.println("We lost " + countNullString + " strings.");
    }

    /**
     * Пример безопасной работы с Weak Reference.
     * Создаем два list-а Weak Reference <String>.
     * В первом листе добавляем объекты в количестве i.
     * Из первого list-а извлекаем String оборачиваем в Weak Reference
     * и добавляем во второй list.
     * Выводим статистику переброшенных объектов.
     */
    private static void exampleSafeWeak() {
        List<WeakReference<String>> weakList = new ArrayList<>();
        List<WeakReference<String>> notNullWeakList = new ArrayList<>();
        for (int i = 0; i < 30_000; i++) {
            weakList.add(new WeakReference<>("Type_" + i));
        }
        System.out.println(" --- Weak Reference --- ");
        System.out.println("Size weak list: " + weakList.size());

        int countNullString = 0;

        for (WeakReference<String> strWeak : weakList) {
            String str = strWeak.get();
            if (str != null) {
                notNullWeakList.add(new WeakReference<>(str));
            } else {
                countNullString++;
            }
        }
        System.out.println("Size not null String list: " + notNullWeakList.size());
        System.out.println("We lost " + countNullString + " strings.");
    }

    /**
     * Примет работа Soft Reference.
     */
    private static void exampleSoft() {
        int mustBe = 0;
        List<SoftReference<String>> softList = new ArrayList<>();
        for (int i = 0; i < 40_000; i++) {
            softList.add(new SoftReference<>("Type_" + i));
            if (("Type_" + i).endsWith("10")) {
                mustBe++;
            }
        }
        int count = 0;
        int pr = 0;

        for (SoftReference<String> str : softList) {
            if (str.get() != null) {
                count++;
                if (Objects.requireNonNull(str.get()).endsWith("10")) {
                    System.out.println(str.get());
                    pr++;
                }
            }
        }
        double result = 0;
        if (count != 0) {
            result = (double) count / softList.size();
        }
        System.out.println(" --- Soft example --- ");
        System.out.println("Number printed objects: " + pr);
        System.out.println("Must be printed objects: " + mustBe);
        System.out.println("Live soft objects: " + count);
        System.out.println("Soft List size: " + softList.size());
        System.out.println("Soft line object - to Total soft object: " + result);
    }

    /**
     * Пример работа Weak Reference
     */
    private static void exampleWeak() {
        int mustBe = 0;
        List<WeakReference<String>> softList = new ArrayList<>();
        for (int i = 0; i < 47_000; i++) {
            softList.add(new WeakReference<>("Type_" + i));
            if (("Type_" + i).endsWith("10")) {
                mustBe++;
            }
        }
            int count = 0;
            int pr = 0;

            for (WeakReference<String> str : softList) {
                if (str.get() != null) {
                    count++;
                    if (Objects.requireNonNull(str.get()).endsWith("10")) {
                        System.out.println(str.get());
                        pr++;
                    }
                }
            }
            double result = 0;
            if (count != 0) {
                result = (double) count / softList.size();
            }
            System.out.println(" --- Weak example --- ");
            System.out.println("Number printed objects: " + pr);
            System.out.println("Must be printed objects: " + mustBe);
            System.out.println("Live weak objects: " + count);
            System.out.println("Weak List size: " + softList.size());
            System.out.println("Weak line object - to Total weak object: " + result);
    }
}

package ru.job4j.gc.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Пример реализации сильных ссылок (Strong Reference).
 */
public class StrongDemo {
    public static void main(String[] args) throws InterruptedException {
        example1();
        /** example2(); */
        /** example3(); */
    }

    /**
     * Пример №1
     * За'null'яем объекты. Вызываем сборщик мусора, он удаляет объекты так как ссылкам присвоен null.
     * Создаем паузу 5 секунд.
     * @throws InterruptedException
     */
    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() {
                    System.out.println("Object removed!");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Пример №2
     * За'null'яем объекты. Вызываем сборщик мусора, он удаляет не только внешние объекты ссылкам на которые присвоим null, а так же и внутренние
     * Создаем паузу 5 секунд.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() { // создаем вложенные объекты
                    @Override
                    protected void finalize() {
                        System.out.println("Remove inner object!");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Пример №3
     * Проблема ссылок (Strong Reference) является то, что если в программе есть неиспользуемые ссылки
     * на созданные объекты, то они не будут удалены, что приведет к утечке памяти,
     * что в свою очередь может привести к ошибке OutOfMemoryException.
     * Чтобы быстрее увидеть ошибку можно выставить VM аргументы -Xmx4m -Xms4m
     */
    private static void example3() {
        List<String> strings = new ArrayList<>();
        while (true) {
            strings.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}

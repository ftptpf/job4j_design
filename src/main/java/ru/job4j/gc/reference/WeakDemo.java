package ru.job4j.gc.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Пример реализации слабых ссылок (Weak Reference).
 * Объекты, на которые ссылаются слабые ссылки удаляются GC сразу, если на них нет сильных или безопасных ссылок.
 * Корректное использование этого типа ссылок аналогично безопасным.
 */
public class WeakDemo {
    public static void main(String[] args) throws InterruptedException {
        example1();
        // example2();
    }

    /**
     * Пример №1
     * За'null'ение сильной ссылки приводит к удалению объекта и мы его также уже не можем получить по слабой ссылке.
     * @throws InterruptedException
     */
    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    /**
     * Пример №2
     * Создаем объект вообще без сильных ссылки. При вызове сборщика мусора они все удаляются.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<>(new Object() {
                @Override
                protected void finalize() {
                    System.out.println("Removed!");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }
}

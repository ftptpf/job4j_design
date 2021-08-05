package ru.job4j.gc.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Пример безопасной работы с SoftReference и WeakReference.
 */
public class SoftWeakReference {
    public static void main(String[] args) {
        //exampleSafeSoft();
        exampleSafeWeak();
    }

    private static void exampleSafeSoft() {
        List<SoftReference<String>> softReferenceList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            softReferenceList.add(new SoftReference<>(String.valueOf(System.currentTimeMillis())));
            if (softReferenceList.get(i) != null) {
                System.out.println(softReferenceList.get(i));
            }
        }
    }

    private static void exampleSafeWeak() {
        String str = "Hello!";
        WeakReference<String> stringWeakReference = new WeakReference<String>(str);
        str = null;
        if (stringWeakReference != null) {
            System.gc();
            System.out.println(stringWeakReference.get());
        }
    }
}

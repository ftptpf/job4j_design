package ru.job4j.gc;

public class UserRunner {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    /**
     * freeMemory - возвращает количество свободной памяти в байтах
     * totalMemory - возвращает общее количество памяти которое может быть использовано
     * maxMemory - возвращает максимальное количество памяти которое может быть использовано
     */
    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        User vika = new User(1, "Vika", "Petrova");
        new User(2, "Sveta", "Nikolaeva");
        vika = null;
        for (int i = 3; i < 7000; i++) {
            new User(i, "Olga", "Ivanova");
        }
        info();
    }
}

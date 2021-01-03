package ru.job4j.generics;

/**
 * Абстрактный класс, базовый.
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

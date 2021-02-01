package ru.job4j.map;

import java.util.Iterator;
import java.util.Objects;

/**
 * Упрощенная реализация HashMap, динамического ассоциативого массива на базе хеш-таблицы.
 * Предусмотрена возможность роста хеш-таблицы при нехватке места для вставки нового элемента.
 * Не предусмотрены методы разрешения коллизий при добавлении новх елементов.
 * Есили при добавлении ключ уже есть - просто возвращается false.
 * Реализовано фиксированное время встравки и получения элементов.
 * @param <K> ключ
 * @param <V> значение
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    private final int DEFAULT_INITIAL_CAPACITY = 16; // константа начальной емкости массива
    private final float MAXIMUM_CAPACITY = 0.75f; // коэффициент загрузки массива
    private int size; // количество добавленных в массив элементов
    private final NodeMap<K, V>[] array; // массив элементов (нодов)
    private int modCount; // счетчик изменений

    public SimpleHashMap() {
        array = new NodeMap[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Метод добавляет значение по ключу.
     * @param key
     * @return Если при добавлении ключ уже есть - возвращает false.
     */
    public boolean insert(K key, V value) {
        return true;
    }

    /**
     * Метод метод возвращает значение по ключу.
     * @param key
     * @return Если ячейка не содержит значения сопоставимого с ключем - возвращается null.
     */
    public V get(K key) {
        return null;
    }

    /**
     * Метод удаляет значение по ключу.
     * @param key
     * @return Если при удалении не был найден ключ - возвращает false.
     */
    public boolean delete(K key) {
        return true;
    }

    /**
     * Метод хеширования ключа.
     * @param key
     * @return
     */
    public int hasFunction(K key) {
        return 0;
    }


    @Override
    public Iterator<K> iterator() {
        return null;
    }


    // Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
    // boolean insert(K key, V value);
    // V get(K key);
    // boolean delete(K key);
    //
    // Реализовывать итератор.
    // Внутренняя реализация должна использовать массив.
    // Нужно обеспечить фиксированное время вставки и получение.
    // Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
    //
    // Методы разрешения коллизий реализовывать не надо.
    // Например: если при добавлении ключ уже есть, то возвращать false.
}

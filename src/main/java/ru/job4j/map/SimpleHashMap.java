package ru.job4j.map;

import java.util.Iterator;
import java.util.Objects;

/**
 * Упрощенная реализация HashMap, динамического ассоциативного массива на базе хеш-таблицы.
 * Предусмотрена возможность роста хеш-таблицы при нехватке места для вставки нового элемента.
 * Не предусмотрены методы разрешения коллизий при добавлении новых элементов.
 * Если при добавлении ключ уже есть - просто возвращается false.
 * Реализовано фиксированное время вставки и получения элементов.
 * @param <K> ключ
 * @param <V> значение
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    static final int DEFAULT_INITIAL_CAPACITY = 16; // константа начальной емкости массива
    static final float MAXIMUM_CAPACITY = 0.75f; // коэффициент загрузки массива
    private int size; // количество добавленных в массив элементов
    private final NodeMap<K, V>[] array; // массив элементов (нодов)
    private int modCount = 0; // счетчик изменений

    public SimpleHashMap() {
        array = new NodeMap[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Метод добавляет значение по ключу.
     * @param key
     * @return Если при добавлении ключ уже есть - возвращает false.
     */
    public boolean insert(K key, V value) {
        int h = key.hashCode();
        NodeMap<K, V> nodeMap= new NodeMap<>()

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
     * Метод позиционирования нода в массиве на основании хеша ключа и размера массива.
     * @param key
     * @return
     */
    public int hashPosition(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) % (array.length - 1);
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

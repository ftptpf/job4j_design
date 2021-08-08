package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/** Абстрактный класс создатель.
 * Структура данных типа кеш. Кеш абстрактный, чтобы можно было задать ключ получения объекта кеша
 * и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 * @param <K> ключ
 * @param <V> объект
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Добавляем данные в кеш.
     * @param key ключ
     * @param value объект
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Извлекаем данные из кеша по ключу.
     * @param key ключ
     * @return объект
     */
    public V get(K key) {
        return cache.get(key).get();
    }

    /**
     *  "Фабричный метод" загрузки содержимого файла по ключу.
     * @param key ключ
     * @return объект
     */
    protected abstract V load(K key);

}

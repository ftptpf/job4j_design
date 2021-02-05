package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
public class SimpleHashMap<K, V> implements Iterable<NodeMap<K, V>> {
    static final float MAXIMUM_CAPACITY = 0.75f; // коэффициент загрузки массива
    private int count; // колличество добавленных в массив элементов
    private NodeMap<K, V>[] array; // массив элементов (нодов)
    private int modCount = 0; // счетчик изменений
    private int arraySize = 16; // начальный размер массива

    public SimpleHashMap() {
        array = new NodeMap[arraySize]; // создаем пустой массив нодов с размерами по умолчанию
    }

    /**
     * Метод добавляет значение по ключу.
     * @param key
     * @return Если при добавлении ключ уже есть - возвращает false.
     */
    public boolean insert(K key, V value) {
        int index = hashIndexPosition(key); // определяем индекс массива по которому будет производиться вставка
        if (Objects.isNull(array[index])) { // если ячейка, к которой мы обратились, пуста
/*        if (array[index].equals(null)) { // если ячейка, к которой мы обратились, пуста*/
            NodeMap<K, V> newNodeMap = new NodeMap<>(key, value); // создаем новый нод
            array[index] = newNodeMap; // добавляем его в ячейку массива
            modCount++; // увеличиваем счетчик произошедших с массивом изменений
            count++; // увеличиваем счетчик добавленных в массив нодов на единицу
            resize(); // вызываем метод проверки массива на заполнение и при необходимости увеличиваем размеры массива
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет степень заполнения массива и если он заполнен более чем определено в
     * коэффициенте заполнения - размер массива увеличивается в два раза.
     * Происходит создание нового массива с перераспределением нодов по новым ячейкам.
     * @return
     */
    private void resize() {
        if (count / arraySize >= MAXIMUM_CAPACITY) { // если превышено значение коэффициента загрузки массива
            NodeMap<K, V>[] newNodeArray; // создаем объект нового расширенного массива
            arraySize *= 2; // увеличиваем заначение размера массива
            newNodeArray = new NodeMap[arraySize]; // инициализируем новый массив новым увеличенным размером
            NodeMap<K, V>[] tempArray = array; // создаем клон старого массива
            array = newNodeArray; // старому массиву присваиваем размер нового массива
            int resizeCount = 0; // количество перенесенных в новый массив элементов

            for (NodeMap<K,V> nod : tempArray) { // берем каждый нод из старого массива
                if (Objects.nonNull(nod)) { // если нод не равен null
                /*if (nod != null) { // если нод не равен null*/
                    int hashIndex = hashIndexPosition(nod.getKey()); // высчитываем новый индекс по ключу нода
                    if (Objects.nonNull(newNodeArray[hashIndex])) { // выполняем проверку содержит ли уже ячейка массива ранее записанный нод
                    /*if (newNodeArray[hashIndex] != null) {*/
                        newNodeArray[hashIndex] = nod; // если ячейка пустая, записывам нод в новый массив
                        resizeCount++;
                    } else {
                        throw new AssertionError("Коллизия при расширении массива. Попытка вставить нод в заполненную ячейку");
                        // System.out.println("Коллизия при расширении массива. Попытка вставить нод в заполненную ячейку");
                    }
                }
            }
            modCount++; // увеличиваем счетчик произошедших с массивом изменений
            System.out.println("В старом массиве было: " + count + " элементов.");
            System.out.println("При расширении массива в новый перенесено: " + resizeCount + " элементов.");
            count = resizeCount; // перезаписываем колличество добавленных элементов массива актуальным значением
        }
    }


    /**
     * Метод метод возвращает значение по ключу.
     * @param key
     * @return Если ячейка не содержит значения сопоставимого с ключём - возвращается null.
     */
    public V get(K key) {
        int index = hashIndexPosition(key); // определяем индекс массива где должен располагаться нод
        if (!array[index].equals(null)) { // проверяем не пуста ли ячейка массива к которой мы обратились
            return array[index].getValue(); // возвращаем значение value нода, который хранится в ячейке массива
        }
        return null;
    }

    /**
     * Метод удаляет значение по ключу.
     * @param key
     * @return Если при удалении не был найден ключ - возвращает false.
     */
    public boolean delete(K key) {
        int index = hashIndexPosition(key); // определяем индекс массива по которому нам необходимо выполнить удаление
        if (!array[index].equals(null)) { // проверяем не пуста ли ячейка массива к которой мы обратились
            array[index] = null; // обнуляем значения которые были записаны в ячейке
            modCount++; // увеличиваем счетчик произошедших с массивом изменений
            count--; // уменьшаем счетчик нодов, которые находятся в массиве
            return true;
        }
        return false;
    }

    /**
     * Метод позиционирования нода в массиве на основании хеша ключа и размера массива.
     * @param key
     * @return
     */
    public int hashIndexPosition(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) % (array.length - 1);
    }

    @Override
    public Iterator<NodeMap<K, V>> iterator() {
        return new Iterator<NodeMap<K, V>>() {
            int index = 0; // текущий индекс в массиве
            int expectedModCount = modCount;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (index < array.length) { // пока индекс меньше размера массива
                    if (Objects.nonNull(array[index])) {
                    // if (array[index] == null); { // если значение ячейки массива равно null
                        // index++;
                        break;
                    }
                    index++; // увеличиваем индекс на единицу, чтобы перейти к проверке другой ячейки
                }
                return index < array.length;
            }

            @Override
            public NodeMap<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return array[index];
            }
        };
    }
}

package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
    // static final int DEFAULT_INITIAL_CAPACITY = 16; // константа начальной емкости массива
    static final float MAXIMUM_CAPACITY = 0.75f; // коэффициент загрузки массива
    private int count; // количество добавленных в массив элементов
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
        if (array[index] == null) { // если ячейка, к которой мы обратились, пуста
            NodeMap<K, V> newNodeMap = new NodeMap<>(key, value); // создаем новыый нод
            array[index] = newNodeMap; // добавляем его в ячеку массива
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
        NodeMap<K, V>[] newNodeArray; // создаем объект нового расширенного массива
        if (count / arraySize >= MAXIMUM_CAPACITY) { // если превышено значение коэффициента загрузки массива
            arraySize *= 2; // увеличиваем значение размера массива
            newNodeArray = new NodeMap[arraySize]; // иничиализируем новый массив новым увеличенным размером
            Iterator<NodeMap<K, V>> itOldArray = new SimpleHashMap<>();  // создаем итератор страрого массива
            NodeMap<K, V> tempNode; // нод временных значений
            int tempIndex = 0;
/*            while (itOldArray.hasNext()) { // пока в массиве есть ноды
                tempNode = itOldArray.next();
                newNodeArray[itOldArray.next().]*/
/*            while (array[tempIndex] != null) {
                tempNode = array[tempIndex];*/
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    tempNode = array[i];

                }
            }


        }


            modCount++; // увеличиваем счетчик произошедших с массивом изменений
            array = newNodeArray; // присваиваем значения нового массивая старому

    }


    /**
     * Метод метод возвращает значение по ключу.
     * @param key
     * @return Если ячейка не содержит значения сопоставимого с ключем - возвращается null.
     */
    public V get(K key) {
        int index = hashIndexPosition(key); // определяем индекс массива где должен располагаться нод
        if (array[index] != null) { // проверяем не пуста ли ячека массива к которой мы обратились
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
        if (array[index] != null ) { // проверяем не пуста ли ячека массива к которой мы обратились
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
            int cursor;
            int expectedModCount = modCount;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != arraySize;
            }

            @Override
            public NodeMap<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return array[cursor];
            }
        };
    }



/*    @Override
    public Iterator<NodeMap<K, V>> iterator() {
        return new Iterator<NodeMap<K, V>>() {
            int cursor;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != arraySize;
            }

            @Override
            public NodeMap<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return array[cursor];
            }
        };
    }*/


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
    //
    // Метод hasNext проверяет, если ли следующий элемент.
    // Многократный вызов этого метода должен быть одинаковым.
    //
    // Метод next возвращает первый элемент ячейки. Второй вызов метода next вернет второй элемент и так далее.
    // Метод next сдвигает указатель итератора. Указатель - это ссылка на элемент, который нужно вернуть.
}

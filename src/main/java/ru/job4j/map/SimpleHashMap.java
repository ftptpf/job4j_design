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
 * MAXIMUM_CAPACITY - коэффициент загрузки массива
 * count - количество добавленных в массив элементов
 * array - массив элементов (нодов)
 * modCount - счетчик изменений
 * arraySize - начальный размер массива
 * @param <K> ключ
 * @param <V> значение
 */
public class SimpleHashMap<K, V> implements Iterable<NodeMap<K, V>> {
    static final float MAXIMUM_CAPACITY = 0.75f;
    private int count;
    private NodeMap<K, V>[] array;
    private int modCount = 0;
    private int arraySize = 16;

    /**
     * Создаем пустой массив нодов с размерами по умолчанию
     */
    public SimpleHashMap() {
        array = new NodeMap[arraySize];
    }

    /**
     * Метод добавляет значение по ключу.
     * hashIndexPosition(key) - определяем индекс массива по которому будет производиться вставка
     * Если ячейка, к которой мы обратились, пуста - создаем новый нод. Добавляем его в ячейку массива.
     * Увеличиваем счетчик произошедших с массивом изменений.
     * Увеличиваем счетчик добавленных в массив нодов на единицу.
     * Вызываем метод проверки массива на заполнение и при необходимости увеличиваем размеры массива.
     * @param key
     * @return Если при добавлении ключ уже есть - возвращает false.
     */
    public boolean insert(K key, V value) {
        int index = hashIndexPosition(key);
        if (Objects.isNull(array[index])) {
            NodeMap<K, V> newNodeMap = new NodeMap<>(key, value);
            array[index] = newNodeMap;
            modCount++;
            count++;
            resize();
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет степень заполнения массива и если он заполнен более чем определено в
     * коэффициенте заполнения - размер массива увеличивается в два раза.
     * Происходит создание нового массива с перераспределением нодов по новым ячейкам.
     * check - высчитываем соотношение заполненных ячеек к размеру массива.
     * Если превышено значение коэффициента загрузки массива - создаем объект нового расширенного массива.
     * Увеличиваем значение размера массива. Инициализируем новый массив новым увеличенным размером.
     * Создаем клон старого массива. Старому массиву присваиваем размер нового массива.
     * Количество перенесенных в новый массив элементов инициализируем нулевым значением.
     * Берем каждый нод из старого массива. Если нод не равен null, высчитываем новый индекс по ключу нода.
     * Выполняем проверку содержит ли уже ячейка массива ранее записанный нод.
     * Если ячейка пуста, записываем нод в новый массив.
     * Увеличиваем счетчик произошедших с массивом изменений.
     * Перезаписываем количество добавленных элементов массива актуальным значением.
     * @return
     */
    private void resize() {
        float check = (float) count / arraySize;
        if (check >= MAXIMUM_CAPACITY) {
            NodeMap<K, V>[] newNodeArray;
            arraySize *= 2;
            newNodeArray = new NodeMap[arraySize];
            NodeMap<K, V>[] tempArray = array;
            array = newNodeArray;
            int resizeCount = 0;

            for (NodeMap<K, V> nod : tempArray) {
                if (Objects.nonNull(nod)) { //
                    int hashIndex = hashIndexPosition(nod.getKey());
                    if (Objects.isNull(newNodeArray[hashIndex])) {
                        newNodeArray[hashIndex] = nod;
                        resizeCount++;
                    } else {
                        throw new AssertionError("Коллизия при расширении массива. Попытка вставить нод в заполненную ячейку");
                    }
                }
            }
            modCount++;
            System.out.println("Произошло расширение массива. В старом массиве было: " + count + " элементов.");
            System.out.println("При расширении массива в новый перенесено: " + resizeCount + " элементов.");
            count = resizeCount;
        }
    }


    /**
     * Метод возвращает значение по ключу.
     * Определяем индекс массива где должен располагаться нод.
     * Проверяем не пуста ли ячейка массива к которой мы обратились, и содержит ли она нужный нам ключ.
     * Возвращаем значение value нода, который хранится в ячейке массива.
     * @param key
     * @return Если ячейка не содержит значения сопоставимого с ключом - возвращается null.
     */
    public V get(K key) {
        int index = hashIndexPosition(key);
        if (Objects.nonNull(array[index]) && Objects.equals(key, array[index].getKey())) {
            return array[index].getValue();
        }
        return null;
    }

    /**
     * Метод удаляет значение по ключу.
     * Определяем индекс массива по которому нам необходимо выполнить удаление.
     * Проверяем не пуста ли ячейка массива к которой мы обратились, и содержит ли она нужный нам ключ.
     * Обнуляем значения которые были записаны в ячейке.
     * Увеличиваем счетчик произошедших с массивом изменений.
     * Уменьшаем счетчик нодов, которые находятся в массиве.
     * @param key
     * @return Если при удалении не был найден ключ - возвращает false.
     */
    public boolean delete(K key) {
        int index = hashIndexPosition(key);
        if (Objects.nonNull(array[index]) && Objects.equals(key, array[index].getKey())) {
            array[index] = null;
            modCount++;
            count--;
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
            int index = 0;
            int expectedModCount = modCount;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (index < array.length) {
                    if (Objects.nonNull(array[index])) {
                        break;
                    }
                    index++;
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
                return array[index++];
            }
        };
    }
}

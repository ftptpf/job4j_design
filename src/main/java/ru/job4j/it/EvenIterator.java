package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращает только четные числа.
 * На входе получает массив чисел.
 */
public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int position = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Метод определяет есть ли следующее четное значение.
     * Возвращает true если в массиве есть четная цифра после position.
     * Возвращает false если в массиве нет четной цифры после position.
     */
    @Override
    public boolean hasNext() {
        int index = findEven(position);
        position = index == -1 ? numbers.length : index;
        return position < numbers.length;
    }

    /**
     * Метод берет значение.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("not more even element after position in array");
        }
        return numbers[position++];
    }

    /**
     * Метод ищет следующее четное число в массиве.
     * index = position
     * rsl - индекс результат
     * Если число под индексом i четное - возвращаем этот индекс как результат.
     * @param index на вход получает текущий индекс.
     * @return возвращает индекс ближайшей четной позиции, если её нет - возвращает -1 (значит метод не нашел после position четных чисел)
     */
    public int findEven(int index) {
        int rsl = -1;
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
}

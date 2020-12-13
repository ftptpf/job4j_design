package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Итератор возвращает только четные числа.
 * На входе получает массив чисел.
 */
public class EvenIterator implements Iterator<Integer> {
    private int[] numbers;
    private int position = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove method not supported in EvenIterator class");
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        throw new UnsupportedOperationException("forEachRemaining method not supported in EvenIterator class");
    }

    /**
     * Метод определяет есть ли следующее четное значение.
     * Возвращает true если в массиве есть четная цифра после position.
     * Возращает false если в массиве нет четной цифры после position.
     */
    @Override
    public boolean hasNext() {
        return findEven(position) >= 0;
    }

    /**
     * Метод берет значение.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("not more even element after position in array");
        }
        int result;
        if (numbers[position] % 2 == 0) { // если это число под индексом 0 и четное
            result = numbers[position]; // возвращаем его как результат
            position++; // смещаем индекс на 1
        } else { // в ином случае
            result = numbers[findEven(position)]; // результатом будет четное число под индексом который получили от метода findEven
            position = findEven(position) + 1; // смещаем индекс на 1
        }
        return result;
    }

    /**
     * Метод ищет следующее четное число в массиве.
     * @param index на вход получает текущий индекс.
     * @return возрашает индекс ближайшей четной поизции, если её нет - возрашает -1.
     */
    public int findEven(int index) { // index = position
        int rsl = -1; // индекс результат
        for (int i = index + 1; i < numbers.length; i++) { // цикл начинается с просмотра индекса "position + 1"
            if (numbers[i] % 2 == 0) { // если число под индексом i четное
                rsl = i; // возвращаем этот индекс как результат
                break;
            }
        }
        return rsl; // если вернется -1, значит метод не нашел после position четных чисел
    }
}

// boolean hasNext(): возвращает true, если в коллекции имеется следующий элемент, иначе возвращает false
// E next(): возвращает текущий элемент и переходит к следующему, если такого нет, то генерируется исключение NoSuchElementException

package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

    /**
     * Метод определяет есть ли следующее четное значение.
     * Возвращает true если в массиве есть четная цифра после position.
     * Возвращает false если в массиве нет четной цифры после position.
     */
    @Override
    public boolean hasNext() {
/*
        position = findEven(position); // присваиваем position индекс четного числа, если конечно четные числа есть
        if (position == -1) { // если четных чисел нет и пришел индекс - 1
            position = numbers.length - 1; // присваиваем position индекс последнего элемента массива
            return false; // и возращаем false
        } else {
            return position >= 0;
        }
*/
        position = findEven(position);
        return position != -1;
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
     * @param index на вход получает текущий индекс.
     * @return возвращает индекс ближайшей четной позиции, если её нет - возвращает -1.
     */
    public int findEven(int index) { // index = position
        int rsl = -1; // индекс результат
        for (int i = index; i < numbers.length; i++) {
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

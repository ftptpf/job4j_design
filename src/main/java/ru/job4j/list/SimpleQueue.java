package ru.job4j.list;

/**
 * Реализация очереди на двух стеках.
 * @param <T>
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод возвращает первое значение и удалять его из (out).
     * @return
     */
    public T poll() {
        if (!in.empty() && out.empty()) {  // если первый список(in) полон, а второй(out) пуст
            while (!in.empty()) { // пока первый список не станет пустым
                out.push(in.pop()); // все элементы первого списка в обратном порядке перебрасываем во второй
            }
        }
        return out.pop();
    }

    /**
     * Метод помещает значение в конец (in).
     * @param value
     */
    public void push(T value) {
        in.push(value);
    }
}

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
     * Если первый список(in) полон, а второй(out) пуст,
     * пока первый список не станет пустым - все элементы первого списка в обратном порядке перебрасываем во второй.
     * @return
     */
    public T poll() {
        if (!in.empty() && out.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
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

package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Каркас универсального хранилища.
 * Так как класс объявлен final он не может иметь подклассы (запрещено наследование).
 * @param <T>
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = indexOf(id);
        boolean rls = index != -1;
        if (rls) {
            mem.set(index, model);
        }
        return rls;
    }

    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        boolean rls = index != -1;
        if (rls) {
            mem.remove(index);
        }
        return rls;
    }

    @Override
    public T findById(String id) {
        int index = indexOf(id);
        return index != -1 ? mem.get(index) : null;
    }

    private int indexOf(String id) {
        int rls = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                rls = i;
                break;
            }
        }
        return rls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemStore<?> memStore = (MemStore<?>) o;
        return mem.equals(memStore.mem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mem);
    }
}

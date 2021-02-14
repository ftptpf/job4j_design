package ru.job4j.control1;

/**
 * Модель данных Info.
 */
public class Info {
    int added; // Сколько добавлено новых пользователей.
    int changed; // Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя, а id осталось прежним.
    int deleted; // Сколько удалено пользователей.

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}

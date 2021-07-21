package ru.job4j.gc;

public class User {
    private int id;
    private String name;
    private String surname;

    public User(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Переопределяем метод который вызывается перед тем как Garbage Collector уничтожает объект
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed: %d, %s, %s%n", id, name, surname);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

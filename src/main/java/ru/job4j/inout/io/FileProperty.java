package ru.job4j.inout.io;

import java.util.Objects;

/**
 * Модель данных файла.
 */
public class FileProperty {
    private String name; // имя файла
    private long size; // размер файла


    public FileProperty(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public String toString() {
        return "FileProperty{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}

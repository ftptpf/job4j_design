package ru.job4j.map;

import java.util.Objects;

public class NodeMap<K, V> {
    final int hash;
    final int key;
    V value;

    public NodeMap(int hash, int key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeMap<?, ?> node = (NodeMap<?, ?>) o;
        return key == node.key && value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "NodeMap{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

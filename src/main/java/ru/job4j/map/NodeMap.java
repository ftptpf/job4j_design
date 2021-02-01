package ru.job4j.map;

import java.util.Objects;

public class NodeMap<K, V> {
    private final int hash;
    private final K key;
    private V value;

    public NodeMap(int hash, K key, V value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    public K getKey() {
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
        return key.equals(node.key) && value.equals(node.value);
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

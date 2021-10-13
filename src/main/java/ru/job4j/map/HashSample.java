package ru.job4j.map;

public class HashSample {
    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        int one = 3;
        int two = 5;
        System.out.println(one + ", " + two);
        byte t = 4;
        System.out.println(t >> 2);
    }
}

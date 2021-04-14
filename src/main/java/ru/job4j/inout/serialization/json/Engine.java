package ru.job4j.inout.serialization.json;

public class Engine {
    private final int power;

    public Engine(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power=" + power
                + '}';
    }
}

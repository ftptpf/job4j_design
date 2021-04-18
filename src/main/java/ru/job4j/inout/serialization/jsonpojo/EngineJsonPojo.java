package ru.job4j.inout.serialization.jsonpojo;

public class EngineJsonPojo {
    private final int power;

    public EngineJsonPojo(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power=" + power
                + '}';
    }
}

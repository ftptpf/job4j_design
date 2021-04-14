package ru.job4j.inout.serialization.json;

import java.util.Arrays;

public class Car {
    private final String brand;
    private final int year;
    private final boolean gas;
    private final Engine engine;
    private final String[] owners;

    public Car(String brand, int year, boolean gas, Engine engine, String... owners) {
        this.brand = brand;
        this.year = year;
        this.gas = gas;
        this.engine = engine;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand='" + brand + '\''
                + ", year=" + year
                + ", gas=" + gas
                + ", engine=" + engine
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }
}

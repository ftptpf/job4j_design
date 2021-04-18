package ru.job4j.inout.serialization.jsonpojo;

import ru.job4j.inout.serialization.json.Engine;

import java.util.Arrays;

public class CarJsonPojo {
    private final String brand;
    private final int year;
    private final boolean gas;
    private final EngineJsonPojo engine;
    private final String[] owners;

    public CarJsonPojo(String brand, int year, boolean gas, EngineJsonPojo engine, String... owners) {
        this.brand = brand;
        this.year = year;
        this.gas = gas;
        this.engine = engine;
        this.owners = owners;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public boolean isGas() {
        return gas;
    }

    public EngineJsonPojo getEngine() {
        return engine;
    }

    public String[] getOwners() {
        return owners;
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

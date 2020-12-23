package ru.job4j.generics;

public class Tiger extends Predator {
    private String color;
    private int power;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "color='" + color + '\'' +
                ", power=" + power +
                '}';
    }
}

package ru.job4j.generics;

public class Predator extends Animal {
    private int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Predator{"
                + "speed=" + speed
                + '}';
    }
}

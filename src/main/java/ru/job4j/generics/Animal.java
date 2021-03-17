package ru.job4j.generics;

public class Animal {
    private int age;
    private double weight;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "age=" + age
                + ", weight=" + weight
                + '}';
    }
}

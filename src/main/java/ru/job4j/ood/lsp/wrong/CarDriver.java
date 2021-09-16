package ru.job4j.ood.lsp.wrong;

public class CarDriver {
    protected String name;
    protected int age;

    public CarDriver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void examMessage() {
        if (age < 16) {
            throw new IllegalArgumentException("You are too young to get a car license!");
        }
        System.out.println("Please contact the administrator to agree on the date of the exam");
    }
}

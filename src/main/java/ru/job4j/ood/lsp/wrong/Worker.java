package ru.job4j.ood.lsp.wrong;

public class Worker {
    protected String name;
    protected int age;
    protected int yearsOfExperience;

    public Worker(String name, int age, int yearsOfExperience) {
        this.name = name;
        this.age = age;
        this.yearsOfExperience = yearsOfExperience;
    }

    public void welcome() {
        System.out.println("Welcome on board bro!");
    }
}

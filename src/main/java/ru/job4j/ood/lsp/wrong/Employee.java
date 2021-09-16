package ru.job4j.ood.lsp.wrong;

public class Employee {
    protected String name;
    protected int age;
    protected int grade;

    public Employee(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public void validate(int grade) {
        if (grade < 0) {
            throw new IllegalArgumentException("Grade can't be less then zero.");
        }
    }

    public void setGrade(int grade) {
        validate(grade);
        this.grade = grade;
    }
}

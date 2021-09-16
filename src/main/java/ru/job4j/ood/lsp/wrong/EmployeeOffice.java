package ru.job4j.ood.lsp.wrong;

/**
 * Нарушение LSP, нарушена инвариантность - условия базового класса не сохранены в подклассе.
 */
public class EmployeeOffice extends Employee {

    public EmployeeOffice(String name, int age, int grade) {
        super(name, age, grade);
    }

    @Override
    public void setGrade(int grade) {
        this.grade = grade;
    }
}

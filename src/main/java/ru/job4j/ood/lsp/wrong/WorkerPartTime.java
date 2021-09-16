package ru.job4j.ood.lsp.wrong;

/**
 * Нарушение LSP, усиление предусловия в подклассе.
 */

public class WorkerPartTime extends Worker {

    public WorkerPartTime(String name, int age, int yearsOfExperience) {
        super(name, age, yearsOfExperience);
    }

    @Override
    public void welcome() {
        if (age > 20 && yearsOfExperience <= 1) {
            throw new IllegalArgumentException("We will call you.");
        }
            System.out.println("Welcome on board bro!");
    }
}

package ru.job4j.ood.lsp.wrong;

/**
 * Нарушение LSP, ослабление постусловия в подклассе.
 */

public class CarDriverFirstSchool extends CarDriver {

    public CarDriverFirstSchool(String name, int age) {
        super(name, age);
    }

    @Override
    public void examMessage() {
        System.out.println("Please contact the administrator to agree on the date of the exam");
    }
}

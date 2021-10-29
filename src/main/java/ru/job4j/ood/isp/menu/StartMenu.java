package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.action.Action;
import ru.job4j.ood.isp.menu.action.SomeAction1;
import ru.job4j.ood.isp.menu.action.SomeAction2;
import ru.job4j.ood.isp.menu.input.ConsoleInput;
import ru.job4j.ood.isp.menu.input.Input;
import ru.job4j.ood.isp.menu.output.Output;
import ru.job4j.ood.isp.menu.output.ConsoleOutput;
import java.util.List;

public class StartMenu {
    private final Output out;

    public StartMenu(Output out) {
        this.out = out;
    }

    public void init(Input input, List<MenuItem> items) {
        boolean run = true;
        while (run) {
            out.show("*** Menu ***");
            consoleMenu(items);
            String select = input.askStr("*** Please, select menu item. e.g. Задача 1.1");
            if (!select.equals("Exit")) {
                out.show("User selected menu item: " + select);
                findActon(select, items);
                out.show(System.lineSeparator());
            } else {
                run = false;
            }
        }
    }

    /**
     * Выводим на меню. Используем рекурсию.
     * @param items пункты меню
     */
    private void consoleMenu(List<MenuItem> items) {
        for (MenuItem item : items) {
            String delimiter = "-".repeat(item.getLevel());
            out.show(delimiter + " " + item.getName());
            if (!item.getChildrenList().isEmpty()) {
                consoleMenu(item.getChildrenList());
            }
        }
    }

    /**
     * Ищем действие соответствущее запрашиваемому меню. Поиск через рекурсию.
     * @param select строка по которой осуществляется поиск
     * @param items пункты меню
     */
    private void findActon(String select, List<MenuItem> items) {
        for (MenuItem item : items) {
            if (select.equals(item.getName())) {
                System.out.println("yes");
                item.getAction().doAction();
            }
            if (!item.getChildrenList().isEmpty()) {
                findActon(select, item.getChildrenList());
            }
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput();
        Action action1 = new SomeAction1();
        Action action2 = new SomeAction2();

        MenuItem menu111 = new MenuItem("Задача 1.1.1", 2, List.of(), action1);
        MenuItem menu112 = new MenuItem("Задача 1.1.2", 2, List.of(), action2);
        MenuItem menu11 = new MenuItem("Задача 1.1", 1, List.of(menu111, menu112), action1);
        MenuItem menu12 = new MenuItem("Задача 1.2", 1, List.of(), action2);
        MenuItem menu1 = new MenuItem("Задача 1", 0, List.of(menu11, menu12), action1);
        MenuItem menu2 = new MenuItem("Exit", 0, List.of());

        List<MenuItem> list = List.of(menu1, menu2);

        new StartMenu(output).init(input, list);
    }
}

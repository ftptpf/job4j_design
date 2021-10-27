package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.action.Action;
import ru.job4j.ood.isp.menu.action.SomeAction1;
import ru.job4j.ood.isp.menu.action.SomeAction2;
import ru.job4j.ood.isp.menu.input.ConsoleInput;
import ru.job4j.ood.isp.menu.input.Input;
import ru.job4j.ood.isp.menu.output.Output;
import ru.job4j.ood.isp.menu.output.ConsoleOutput;
import ru.job4j.ood.isp.menu.store.MemStore;
import ru.job4j.ood.isp.menu.store.Store;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StartMenu {
    private final Output out;

    public StartMenu(Output out) {
        this.out = out;
    }

    public void init(Input input, List<MenuItem> items) {
        boolean run = true;
        while (run) {
            consoleMenu(items);
            String select = input.askStr("*** Please, select menu item. e.g. Задача 1.1 :");
            if (!select.equals("Exit")) {
                out.show("User selected menu item: " + select);
            } else {
                run = false;
            }
        }
    }

    private void consoleMenu(List<MenuItem> items) {
        out.show("*** Menu ***");
        for (MenuItem item : items) {
            out.show(item.getName());

        }
    }


    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput();
        // Store store = new MemStore();
        Action action1 = new SomeAction1();
        Action action2 = new SomeAction2();

        MenuItem menuItemTask1 = new MenuItem("Задача 1.",  1, 0, action1);
        MenuItem menuItemTask11 = new MenuItem("Задача 1.1", 1, 1, menuItemTask1, action1);
        MenuItem menuItemTask12 = new MenuItem("Задача 1.2", 2, 1, menuItemTask1, action1);
        MenuItem menuItemTask111 = new MenuItem("Задача 1.1.1", 1, 2, menuItemTask11, action1);
        MenuItem menuItemTask112 = new MenuItem("Задача 1.1.2", 1, 2, menuItemTask11, action1);
        MenuItem menuItemExit = new MenuItem("Exit", 2 ,0, action2);

        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(menuItemTask1);
        menuItemList.add(menuItemTask11);
        menuItemList.add(menuItemTask12);
        menuItemList.add(menuItemTask111);
        menuItemList.add(menuItemTask112);
        menuItemList.add(menuItemExit);

        menuItemList.sort(Comparator.comparing(MenuItem::getLevel)
                .thenComparing(MenuItem::getId));

        new StartMenu(output).init(input, menuItemList);
    }
}

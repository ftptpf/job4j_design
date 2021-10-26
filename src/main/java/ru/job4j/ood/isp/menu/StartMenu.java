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

import java.util.Scanner;

public class StartMenu {
    private final Output out;

    public StartMenu(Output out) {
        this.out = out;
    }

    public void init(Scanner scanner) {
        boolean run = true;
        while (run) {
            consoleMenu();
            System.out.println("Select: ");
            String select = scanner.nextLine();
            if (!select.equals("Exit")) {
                System.out.println("Пользователь выбрал меню: " + select);
            } else {
                run = false;
            }
        }
    }

    private void consoleMenu() {
        Output menu = new ConsoleOutput();
        // menu.show(listMenu);
    }


    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput();
        Store store = new MemStore();
        Action action1 = new SomeAction1();
        Action action2 = new SomeAction2();

        MenuItem menuItemTask1 = new MenuItem("Задача 1.", 0, action1);
        MenuItem menuItemTask11 = new MenuItem("Задача 1.1", 1, menuItemTask1, action1);
        MenuItem menuItemTask12 = new MenuItem("Задача 1.2", 1, menuItemTask1, action1);
        MenuItem menuItemTask111 = new MenuItem("Задача 1.1.1", 2, menuItemTask11, action1);
        MenuItem menuItemTask112 = new MenuItem("Задача 1.1.2", 2, menuItemTask11, action1);
        MenuItem menuItemExit = new MenuItem("Exit", 0, action2);



    }
}

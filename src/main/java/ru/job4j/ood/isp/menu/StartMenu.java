package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.action.Action;
import ru.job4j.ood.isp.menu.action.SomeAction1;
import ru.job4j.ood.isp.menu.action.SomeAction2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StartMenu {
    public void init(Scanner scanner) {


    }

    private void showMenu() {
        List<MenuItem> listMenu = new ArrayList<>();
/*        MenuItem menuLevel1Item1;
        MenuItem menuLevel2Item1;
        MenuItem menuLevel2Item2;
        MenuItem menuLevel3Item1;
        MenuItem menuLevel3Item2;*/
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MenuItem menuLevel1Item1;
        MenuItem menuLevel2Item1;
        MenuItem menuLevel2Item2;
        MenuItem menuLevel3Item1;
        MenuItem menuLevel3Item2;

        Action action1 = new SomeAction1();
        Action action2 = new SomeAction2();

        menuLevel3Item1 = new MenuItem("Задача 1.1.1.", new ArrayList<>(), action1);
        menuLevel3Item2 = new MenuItem("Задача 1.1.2.", new ArrayList<>(), action2);
        List<MenuItem> listLevel3 = List.of(menuLevel3Item1, menuLevel3Item2);
        menuLevel2Item1 = new MenuItem("Задача 1.1.", listLevel3, action1);
        menuLevel2Item2 = new MenuItem("Задача 1.2.", new ArrayList<>(), action2);
        List<MenuItem> listLevel2 = List.of(menuLevel2Item1, menuLevel2Item2);
        menuLevel1Item1 = new MenuItem("Задача 1.1.1.", new ArrayList<>(), action1);
        List<MenuItem> listLevel1 = List.of(menuLevel1Item1);

        new StartMenu().init(input);

    }
}

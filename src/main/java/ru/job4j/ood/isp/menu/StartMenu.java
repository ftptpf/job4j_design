package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartMenu {
    public void init(Scanner scanner) {


    }

    private void showMenu() {
        List<MenuItem> listMenu = new ArrayList<>();
        MenuItem menuLevel1Item1;
        MenuItem menuLevel2Item1;
        MenuItem menuLevel2Item2;
        MenuItem menuLevel3Item1;
        MenuItem menuLevel3Item2;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        new StartMenu().init(input);


    }

}

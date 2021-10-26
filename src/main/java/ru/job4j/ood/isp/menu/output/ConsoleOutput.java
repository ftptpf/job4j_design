package ru.job4j.ood.isp.menu.output;

import ru.job4j.ood.isp.menu.MenuItem;

import java.util.List;

public class ConsoleOutput implements Output {

    @Override
    public void show(List<MenuItem> listMenu) {
        for (MenuItem menuItem : listMenu) {
            String result = menuItem.getName();
            System.out.println(result);
        }
    }
}

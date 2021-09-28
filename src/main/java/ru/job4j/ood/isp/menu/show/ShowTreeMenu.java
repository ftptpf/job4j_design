package ru.job4j.ood.isp.menu.show;

import ru.job4j.ood.isp.menu.MenuItem;

import java.util.List;

public class ShowTreeMenu implements ShowMenu {
    List<MenuItem> list;

    public ShowTreeMenu(List<MenuItem> list) {
        this.list = list;
    }

    @Override
    public void showMenu() {
        for (MenuItem menuItem : list) {
            String result = menuItem.getName();
            System.out.println(result);
            while (!menuItem.getChildrenList().isEmpty()) {
                showMenu();
            }
        }
    }
}

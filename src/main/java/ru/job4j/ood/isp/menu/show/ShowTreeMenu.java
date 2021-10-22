package ru.job4j.ood.isp.menu.show;

import ru.job4j.ood.isp.menu.MenuItem;

import java.util.List;

public class ShowTreeMenu implements ShowMenu {

    @Override
    public void showMenu(List<MenuItem> listMenu) {
        for (MenuItem menuItem : listMenu) {
            String result = menuItem.getName();
            System.out.println(result);
            while (!menuItem.getChildrenList().isEmpty()) {
                showMenu(listMenu);
            }
        }
    }
}

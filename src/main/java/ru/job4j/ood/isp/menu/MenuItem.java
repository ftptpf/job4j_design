package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.action.Action;

import java.util.List;
import java.util.Objects;

/**
 * Модель данных пункта меню.
 * name - имя
 * level - уровень расположения пункт меню
 * childrenList - лист дочерних подменю
 * parent - родительский пункт меню
 * action - действие выполняемое пунктом меню
 */
public class MenuItem {
    private String name;
    int level;
    private List<MenuItem> childrenList;
    private Action action;

    public MenuItem(String name, int level, List<MenuItem> childrenList, Action action) {
        this.name = name;
        this.level = level;
        this.childrenList = childrenList;
        this.action = action;
    }

    public MenuItem(String name, int level, List<MenuItem> childrenList) {
        this.name = name;
        this.level = level;
        this.childrenList = childrenList;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<MenuItem> childrenList) {
        this.childrenList = childrenList;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return level == menuItem.level && name.equals(menuItem.name) && childrenList.equals(menuItem.childrenList) && action.equals(menuItem.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, childrenList, action);
    }
}

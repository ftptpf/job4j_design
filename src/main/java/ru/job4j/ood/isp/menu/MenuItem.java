package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.action.Action;

import java.util.List;

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
    int id;
    int level;
    private List<MenuItem> childrenList;
    private MenuItem parent;
    private Action action;

    public MenuItem(String name, int level, Action action) {
        this.name = name;
        this.level = level;
        this.action = action;
    }

    public MenuItem(String name, int id, int level, Action action) {
        this.name = name;
        this.id = id;
        this.level = level;
        this.action = action;
    }

    public MenuItem(String name, int id,  int level, MenuItem parent, Action action) {
        this.name = name;
        this.level = level;
        this.parent = parent;
        this.action = action;
    }

    public MenuItem(String name, int level, List<MenuItem> childrenList, Action action) {
        this.name = name;
        this.level = level;
        this.childrenList = childrenList;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

}

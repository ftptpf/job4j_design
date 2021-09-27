package ru.job4j.ood.isp.menu;

import java.util.List;

public class MenuItem {
    private String name;
    private List<MenuItem> childrenList;
    private MenuItem parent;
    Action action;

    public MenuItem(String name, List<MenuItem> childrenList, MenuItem parent, Action action) {
        this.name = name;
        this.childrenList = childrenList;
        this.parent = parent;
        this.action = action;
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

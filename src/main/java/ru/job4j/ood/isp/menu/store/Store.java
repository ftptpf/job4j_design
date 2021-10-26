package ru.job4j.ood.isp.menu.store;

import ru.job4j.ood.isp.menu.MenuItem;

import java.util.List;

public interface Store {
    MenuItem add(MenuItem item);
    List<MenuItem> findAll();
    List<MenuItem> findByName();
}

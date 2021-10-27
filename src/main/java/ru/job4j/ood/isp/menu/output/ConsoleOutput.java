package ru.job4j.ood.isp.menu.output;

import ru.job4j.ood.isp.menu.MenuItem;

import java.util.List;

public class ConsoleOutput implements Output {

    @Override
    public void show(Object obj) {
        System.out.println(obj);
    }
}

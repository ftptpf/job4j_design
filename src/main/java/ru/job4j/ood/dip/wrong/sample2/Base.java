package ru.job4j.ood.dip.wrong.sample2;

/**
 * Нарушение принципа DIP.
 * Объект находится в поле класса. конструкторе и аргументах метода.
 * Должна быть реализация через интерфейс List, лучше с generic-ками.
 * Возможно еще лучше с выделением отдельной абстракции (интерфейса) для хранения информации.
 * Метод addInfo можно было бы написать как public void addInfo(T key, V value)
 */

import java.util.HashMap;

public class Base {
    private HashMap<Integer, String> map = new HashMap<>();

    public Base(HashMap<Integer, String> map) {
        this.map = map;
    }

    public void addInfo(Integer integer, String string) {
        map.put(integer, string);
    }
}

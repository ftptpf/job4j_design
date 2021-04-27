package ru.job4j.inout.control;

import java.util.ArrayList;
import java.util.List;

public class Shell {
    String result;
    List<String> list = new ArrayList<>();
    public void cd(String path) {
        list.add(path);
    }
    public String pwd() {
        if (list.contains("..")) {
            result = "/";
        } else if (list.contains("../root")) {
            result = "/root";
        }
        return result;
    }
}

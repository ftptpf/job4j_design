package ru.job4j.inout.exam;

import java.util.*;

public class Shell {
    String result;
    List<String> list = new ArrayList<>();
    public void cd(String path) {
        if (path.startsWith("/")) {
            parseAbsolute(path);
        } else {
            parseRelative(path);
        }
        //list.add(path);
    }
    public String pwd() {
/*        int lastIndex = list.size() - 1;
        String last = list.get(lastIndex);
        if (last.equals("..") || last.equals("/")) {
            result = "/";
        } else if (last.equals("../root")) {
            result = "/root";
        } else if (list.size() == 2 && (list.get(0).startsWith("/") && list.get(1).startsWith("/"))) {
            result = list.get(1);
        } else if (list.size() == 2 && !list.contains("/")) {
            result = "/" + list.get(0) + "/" + list.get(1);
        }*/
        return result;
    }

    public static void parseAbsolute(String path) {
        String[] array = path.split("/");
        Deque<String> stack = new ArrayDeque<>();


    }

    public static void parseRelative(String path) {
    }
}

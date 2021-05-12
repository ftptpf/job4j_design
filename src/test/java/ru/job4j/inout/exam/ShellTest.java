package ru.job4j.inout.exam;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShellTest {

    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd("/user"); // указываем путь к директории
        shell.cd("../root"); // переходим в родительский каталог и из него в папку root
        assertThat(
                shell.pwd(), is("/root")
        );
    }

    @Test
    public void whenAbsolutePath() {
        Shell shell = new Shell();
        shell.cd("/path/to/file"); // указываем путь к директории
        shell.cd("/new/path/to/my/file"); // явно указываем новый путь к директории
        assertThat(shell.pwd(), is("/new/path/to/my/file"));
    }

    @Test
    public void whenCdRoot() {
        Shell shell = new Shell();
        shell.cd("/"); // указываем путь к корневой директории
        assertThat(
                shell.pwd(), is("/")
        );
    }

    @Test
    public void whenCdUserLocal() {
        Shell shell = new Shell();
        shell.cd("user"); // указываем папку, по факту /user
        shell.cd("local"); // переходим в папку local, которая хранится в user
        assertThat(
                shell.pwd(), is("/user/local")
        );
    }

    @Test
    public void whenCdUserBack() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd(".."); // переходим в родительский каталог
        assertThat(
                shell.pwd(), is("/")
        );
    }
}
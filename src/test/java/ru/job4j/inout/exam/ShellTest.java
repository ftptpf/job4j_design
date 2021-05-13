package ru.job4j.inout.exam;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShellTest {

    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd("/user"); // указываем абсолютный путь к директории
        shell.cd("../root"); // из папки user сначала поднимаемся в родительскую директорию, потом опускаемся в папку root
        assertThat(
                shell.pwd(), is("/root")
        );
    }

    @Test
    public void whenAbsolutePath() {
        Shell shell = new Shell();
        shell.cd("/path/to/file"); // указываем абсолютный путь к директории
        shell.cd("/new/path/to/my/file"); // указываем новый абсолютный путь к директории
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
        shell.cd("local"); // переходим в папку local, которая хранится внутри папки user
        assertThat(
                shell.pwd(), is("/user/local")
        );
    }

    @Test
    public void whenCdUserBack() {
        Shell shell = new Shell();
        shell.cd("user"); // указываем папку, по факту /user
        shell.cd(".."); // переходим в родительский каталог папки user
        assertThat(
                shell.pwd(), is("/")
        );
    }
}
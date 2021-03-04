package ru.job4j.inout;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenKeyValue() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.password"),
                is("password")
        );
    }

    @Test
    public void whenWithoutValue() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("hibernate.connection.data"));
/*        assertThat(
                config.value("hibernate.connection.data"),
                is("null")*/
        //);
    }
}
package ru.job4j.jdbc;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Подключение к базе данных.
 * path - путь к properties файлу
 * values - настройки (properties)
 */
public class ConnectionDemo {
    private static Path path = Paths.get("resources/app.properties");
    private static Properties values = new Properties();

    /**
     * Загрузка из properties файла параметров настроек (url, login, password).
     */
    public static void loadProperties() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            values.load(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        loadProperties();
        Class.forName("org.postgresql.Driver");
        String url = values.getProperty("db.url");
        String login = values.getProperty("db.login");
        String password = values.getProperty("db.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}

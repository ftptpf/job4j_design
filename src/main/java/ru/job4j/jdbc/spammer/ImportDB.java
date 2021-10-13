package ru.job4j.jdbc.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Из текстового файла переносим данные в postgreSQL.
 * cfg - файл где хранятся параметры базы данных
 * dump - текстовый файл который мы парсим
 */
public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Считываем данные из текстового файла dump.
     * Из каждой строки dump формируем массив строк по разделителю ";".
     * На базе имени и email каждой строки создаем юзера и добавляем в лист юзеров.
     * @return лист юзеров
     * @throws IOException
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(string -> {
                String[] array = string.split(";");
                users.add(new User(array[0], array[1]));
            });
        }
        return users;
    }

    /**
     * Инициализируем соедениение с базой данных PostgreSQL и сохраняем в нее ранее считанные данный.
     * @param users
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO spam_db(name, email) VALUES(? , ?)")) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.execute();
                }
            }
        }
    }

    /**
     * Класс структуры данных пользователей.
     */
    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("resources/spam/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "resources/spam/dump.txt");
        db.save(db.load());
    }
}

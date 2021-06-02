package ru.job4j.jdbc.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Из текстового файла переносим данные в postgreSQL.
 */
public class ImportDB {
    private Properties cfg; // файл где хранятся параметры базы данных
    private String dump; // текстовый файл который мы парсим

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Считываем данные с текстового файла.
     * @return
     * @throws IOException
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            Map<String, String> map = new HashMap<>();
            String[] array  = rd.lines()
                    .forEach(str -> Arrays.stream(str.split(";", 2))
                            .collect(Collectors.flatMapping(s -> s[0], )));
                    //.map(str -> str.split(";", 2))
                            //.collect(Collectors.toMap(s -> s[0], s -> s[1]));
            //String[] strings = rd.lines().forEach(str -> str.split(";", 2)));
            //rd.lines().forEach(str -> Arrays.stream(str.split(";")).collect(Collectors.toList()));
/*
            rd.lines()
                    .forEach(
                            str -> Arrays.stream(
                                    str.split(";", 2))
                                    .map(Collectors.toList(new User())));
*/

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

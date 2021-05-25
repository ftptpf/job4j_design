package ru.job4j.jdbc.statement;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private static Properties properties = new Properties();
    private static Path path = Paths.get("resources/jdbc/tableEditor.properties");

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    /**
     * Загрузка из properties файла параметров настроек (url, login, password).
     */
    public static void loadProperties() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            properties.load(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создаем соединение с базой данных.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void initConnection() throws ClassNotFoundException, SQLException {
        loadProperties();
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("db.url");
        String login = properties.getProperty("db.login");
        String password = properties.getProperty("db.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * Создаем пустую таблицу без столбцов с указанным именем.
     * @param tableName имя таблицы
     */
    public void createTable(String tableName) throws SQLException {
        stringStatementExecute(
                String.format(
                        "CREATE TABLE IF NOT EXISTS %s ();",
                        tableName
                )
        );
    }

    /**
     * Удаляем таблицу по указанному имени.
     * @param tableName имя таблицы
     */
    public void dropTable(String tableName) throws SQLException {
        stringStatementExecute(
                String.format(
                        "DROP TABLE IF EXISTS %s;",
                        tableName
                )
        );
    }

    /**
     * Добавляет столбец в таблицу.
     * @param tableName имя таблицы
     * @param columnName имя колонки
     * @param type тип
     */
    public void addColumn(String tableName, String columnName, String type) throws SQLException, ClassNotFoundException {
        stringStatementExecute(
                String.format(
                        "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s;",
                        tableName,
                        columnName,
                        type
                )
        );
    }

    /**
     * Удаляет столбец из таблицы.
     * @param tableName имя таблицы
     * @param columnName имя колонки
     */
    public void dropColumn(String tableName, String columnName) throws SQLException {
        stringStatementExecute(
                String.format(
                        "ALTER TABLE %s DROP COLUMN IF EXISTS %s;",
                        tableName,
                        columnName
                )
        );
    }

    /**
     * Переименовываем столбец.
     * @param tableName имя таблицы
     * @param columnName имя колонки
     * @param newColumnName новое имя колонки
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        stringStatementExecute(
                String.format(
                        "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                        tableName,
                        columnName,
                        newColumnName
                )
        );
    }

    /**
     * Вспомогательный матод выполнения SQL оперций.
     * @param sql сторока c SQL скриптом
     * @throws SQLException
     */
    public void stringStatementExecute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    /**
     * Получаем схему таблицы.
     * @param tableName имя таблицы
     * @return схема
     * @throws SQLException
     */
    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    /**
     * Проверяем существует ли таблица.
     * @param tableName имя таблицы
     * @return true - если таблица существует, false - если таблицы нет
     * @throws SQLException
     */
    public boolean ifTableExist(String tableName) throws SQLException {
        boolean isExist = false;
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = ?);")) {
            pStatement.setString(1, tableName);
            try (ResultSet result = pStatement.executeQuery()) {
                if (result.next()) {
                    isExist = result.getBoolean("EXISTS");
                }
            }
            return isExist;
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

package ru.job4j.jdbc.statement;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class TableEditor implements AutoCloseable{
    private Connection connection;
    private Properties properties;
    private Path path;

    public TableEditor(Properties properties) throws ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException {
        properties = new Properties();
        path = Paths.get("resources/jdbc/app.properties");
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("db.url");
        String login = properties.getProperty("db.login");
        String password = properties.getProperty("db.password");

        connection = null;
    }

    /**
     * Создаем пустую таблицу без столбцов с указанным именем.
     * @param tableName имя таблицы
     */
    public void createTable(String tableName) {

    }

    /**
     * Удаляем таблицу по указанному имени.
     * @param tableName имя таблицы
     */
    public void dropTable(String tableName) {

    }

    /**
     * Добавляет столбец в таблицу.
     * @param tableName имя таблицы
     * @param columnName имя колонки
     * @param type тип
     */
    public void addColumn(String tableName, String columnName, String type) {

    }

    /**
     * Удаляет столбец из таблицы.
     * @param tableName имя таблицы
     * @param columnName имя колонки
     */
    public void dropColumn(String tableName, String columnName) {

    }

    /**
     * Переименовываем столбец.
     * @param tableName имя таблицы
     * @param columnName имя колонки
     * @param newColumnName новое имя колонки
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {

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
                        columns.getString("COLUMNS_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

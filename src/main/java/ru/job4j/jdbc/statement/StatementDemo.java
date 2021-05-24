package ru.job4j.jdbc.statement;

import java.sql.*;

public class StatementDemo {
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS demo_table(%s, %s);",
                        "id SERIAL PRIMARY KEY",
                        "name VARCHAR(255)"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));

            }
        }
    }

    private static String getTableScheme(Connection connection, String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, "type")) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                columns.getString("COLUMN_NAME"),
                columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }
}

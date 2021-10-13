package ru.job4j.jdbc.prepare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() throws ClassNotFoundException, SQLException {
        initConnection();
    }

    public void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) {
        try (PreparedStatement pStatement =
                     connection.prepareStatement("INSERT INTO cities (name, population) VALUES (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            pStatement.setString(1, city.getName());
            pStatement.setInt(2, city.getPopulation());
            pStatement.execute();
            try (ResultSet getGeneratedKey = pStatement.getGeneratedKeys()) {
                if (getGeneratedKey.next()) {
                    city.setId(getGeneratedKey.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement pStatement =
                connection.prepareStatement("UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            pStatement.setString(1, city.getName());
            pStatement.setInt(2, city.getPopulation());
            pStatement.setInt(3, city.getId());
            result = pStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement pStatement =
                     connection.prepareStatement("DELETE FROM cities WHERE id = ?")) {
            pStatement.setInt(1, id);
            result = pStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement pStatement =
                connection.prepareStatement("SELECT * FROM cities")) {
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        City oneCity = new City(1, "NY", 25_000_000);
        City twoCity = new City(2, "Moscow", 15_000_000);
        PrepareStatementDemo ps = new PrepareStatementDemo();
        ps.delete(1);
        ps.update(twoCity);
        List<City> list = ps.findAll();
        for (City city : list) {
            System.out.println(city);
        }
    }
}

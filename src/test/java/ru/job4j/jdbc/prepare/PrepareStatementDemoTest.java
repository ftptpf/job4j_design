package ru.job4j.jdbc.prepare;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class PrepareStatementDemoTest {

    City cityOne = new City(1, "NY", 25000000);
    City cityTwo = new City(2, "Moscow", 15000000);

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        PrepareStatementDemo pStatement = new PrepareStatementDemo();
        pStatement.insert(cityOne);
        assertEquals("1 NY 250000000", pStatement.findAll());
    }
}
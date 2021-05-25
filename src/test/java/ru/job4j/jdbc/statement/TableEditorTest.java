package ru.job4j.jdbc.statement;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;

public class TableEditorTest {

    String tableName = "sample_table";
    String firstColumnName = "car";
    String secondColumnName = "engine";
    String type = "VARCHAR(255)";

    @Test
    public void createTable() {
        try {
            TableEditor tableEditor = new TableEditor(new Properties());
            tableEditor.dropTable(tableName);
            tableEditor.createTable(tableName);
            assertEquals("column          type           " + System.lineSeparator(), tableEditor.getScheme(tableName));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addColumnTable() {
        try {
            TableEditor tableEditor = new TableEditor(new Properties());
            tableEditor.dropTable(tableName);
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, firstColumnName, type);
            assertEquals("column          type           "
                    + System.lineSeparator()
                    + "car             varchar        "
                    + System.lineSeparator(), tableEditor.getScheme(tableName));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void renameColumn() {
        try {
            TableEditor tableEditor = new TableEditor(new Properties());
            tableEditor.dropTable(tableName);
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, firstColumnName, type);
            assertEquals("column          type           "
                    + System.lineSeparator()
                    + "car             varchar        "
                    + System.lineSeparator(), tableEditor.getScheme(tableName));
            tableEditor.renameColumn(tableName, firstColumnName, secondColumnName);
            assertEquals("column          type           "
                    + System.lineSeparator()
                    + "engine          varchar        "
                    + System.lineSeparator(), tableEditor.getScheme(tableName));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dropColumn() {
        try {
            TableEditor tableEditor = new TableEditor(new Properties());
            tableEditor.dropTable(tableName);
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, firstColumnName, type);
            assertEquals("column          type           "
                    + System.lineSeparator()
                    + "car             varchar        "
                    + System.lineSeparator(), tableEditor.getScheme(tableName));
            tableEditor.addColumn(tableName, secondColumnName, type);
            assertEquals("column          type           "
                    + System.lineSeparator()
                    + "car             varchar        "
                    + System.lineSeparator()
                    + "engine          varchar        "
                    + System.lineSeparator(), tableEditor.getScheme(tableName));
            tableEditor.dropColumn(tableName, firstColumnName);
            assertEquals("column          type           "
                    + System.lineSeparator()
                    + "engine          varchar        "
                    + System.lineSeparator(), tableEditor.getScheme(tableName));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void dropTable() {
        try {
            TableEditor tableEditor = new TableEditor(new Properties());
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, secondColumnName, type);
            tableEditor.dropTable(tableName);
            assertFalse(tableEditor.ifTableExist(tableName));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

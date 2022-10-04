package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void initStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        initStatement(String.format(
                "create table if not exists %s();", tableName));
    }

    public void dropTable(String tableName) {
        initStatement(String.format(
                "drop table if exists %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        initStatement(String.format(
                "alter table %s add column %s %s;",
                tableName,
                columnName,
                type));
    }

    public void dropColumn(String tableName, String columnName) {
        initStatement(String.format(
                "alter table %s drop column %s;",
                tableName,
                columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        initStatement(String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName));
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor
                .class.getClassLoader()
                .getResourceAsStream("table_editor.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("table1");
            System.out.println(getTableScheme(tableEditor.connection, "table1"));
            tableEditor.addColumn("table1", "name", "varchar");
            System.out.println(getTableScheme(tableEditor.connection, "table1"));
            tableEditor.renameColumn("table1", "name", "newname");
            System.out.println(getTableScheme(tableEditor.connection, "table1"));
            tableEditor.dropColumn("table1", "newname");
            System.out.println(getTableScheme(tableEditor.connection, "table1"));
            tableEditor.dropTable("table1");
        }
    }
}

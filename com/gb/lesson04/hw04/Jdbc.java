package com.gb.lesson04;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database.db");
        prepareTables(connection);
        insertData(connection);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id, name, author from books");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                System.out.println(id + " " + name + " " + author);
            }
        }
        connection.close();

    }

    private static void prepareTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table books (id bigint, name varchar(255), author varchar(255))");
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into books(id, name, author) values(1, 'book #1', 'author #1')");
            statement.execute("insert into books(id, name, author) values(2, 'book #2', 'author #2')");
            statement.execute("insert into books(id, name, author) values(3, 'book #3', 'author #3')");
            statement.execute("insert into books(id, name, author) values(4, 'book #4', 'author #4')");
            statement.execute("insert into books(id, name, author) values(5, 'book #5', 'author #5')");
            statement.execute("insert into books(id, name, author) values(6, 'book #6', 'author #6')");
            statement.execute("insert into books(id, name, author) values(7, 'book #7', 'author #7')");
            statement.execute("insert into books(id, name, author) values(8, 'book #8', 'author #8')");
            statement.execute("insert into books(id, name, author) values(9, 'book #9', 'author #9')");
            statement.execute("insert into books(id, name, author) values(10, 'book #10', 'author #10')");


        }
    }

}

package com.njves.empspent.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;
    private static Database instance;

    private Database() {
        try {
            init();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:emp.sqlite");
        System.out.println("Подключил базу данных");
        createTable();
    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS speciality(id INTEGER PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "salary INTEGER NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS employee(id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "speciality INTEGER NOT NULL," +
                "FOREIGN KEY(speciality) REFERENCES speciality(id))");

        statement.execute("CREATE TABLE IF NOT EXISTS required_spec(id INTEGER PRIMARY KEY," +
                "spec_id INTEGER NOT NULL," +
                "emp_capacity INTEGER NOT NULL," +
                "FOREIGN KEY(spec_id) REFERENCES speciality(id))");

        statement.execute("CREATE TABLE IF NOT EXISTS working_days(id INTEGER PRIMARY KEY," +
                "emp_id INTEGER NOT NULL," +
                "work_day DATE NOT NULL," +
                "is_work INTEGER NOT NULL CHECK(is_work in (0, 1))," +
                "FOREIGN KEY(emp_id) REFERENCES employees(id))");
    }

}

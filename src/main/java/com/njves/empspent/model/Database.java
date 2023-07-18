/**
 * Модуль содержащий класс базы данных
 */
package com.njves.empspent.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Модель связи с базой данных - синглтон
 */
public class Database {
    /**
     * Объект соединения
     */
    private Connection connection;

    /**
     * Объект текущего класса
     */
    private static Database instance;

    /**
     * Приватный конструктор
     */
    private Database() {
        try {
            init();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает соединение с базой данных
     * @return соединение
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Возвращает объект базы данных
     * @return объект
     */
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Инициализиурет соединение
     * @throws ClassNotFoundException выбрасывается если драйвер не найден
     * @throws SQLException прочие ошибки SQL
     */
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:emp.sqlite");
        System.out.println("Подключил базу данных");
        createTable();
    }

    /**
     * Создает таблицы в базе данных
     * @throws SQLException прочие ошибки SQL
     */
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

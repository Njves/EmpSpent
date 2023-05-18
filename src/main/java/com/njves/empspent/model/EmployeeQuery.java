package com.njves.empspent.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeQuery extends Query<Employee> {
    @Override
    List<Employee> select() {
        List<Employee> employees = new ArrayList<>();
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM employee");
            while(resultSet.next()) {
                employees.add(new Employee(resultSet.getInt(1), resultSet.getString(2), getSpeciality(resultSet.getInt(3))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    List<Employee> select(Employee param) {
        return null;
    }

    @Override
    Employee selectObject(Employee param) {
        Employee employee = null;
        try {
            ResultSet resultSet = database.connection.createStatement().executeQuery("SELECT * FROM employee WHERE id = " + param.getId());
            while (resultSet.next())
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), getSpeciality(resultSet.getInt(3)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    void insert(Employee object) {
        PreparedStatement statement;
        try {
            statement = database.connection.prepareStatement("""
            INSERT INTO employee(name, speciality) VALUES(?, ?)
            """);

            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Добавил нового пользователя: " + object);
    }

    @Override
    void update(Employee object) {

    }
}

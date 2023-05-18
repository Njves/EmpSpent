package com.njves.empspent.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeQuery extends Query<Employee> {
    @Override
    public List<Employee> select() {
        Query<Speciality> specialityQuery = new SpecialityQuery();
        List<Employee> employees = new ArrayList<>();
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM employee");
            while(resultSet.next()) {
                Speciality speciality = new Speciality();
                speciality.setId(resultSet.getInt(3));
                employees.add(new Employee(resultSet.getInt(1), resultSet.getString(2), specialityQuery.selectObject(speciality)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public List<Employee> select(Employee param) {
        return null;
    }

    @Override
    public Employee selectObject(Employee param) {
        Query<Speciality> specialityQuery = new SpecialityQuery();
        Employee employee = null;
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM employee WHERE id = " + param.getId());
            while (resultSet.next()) {
                Speciality speciality = new Speciality();
                speciality.setId(resultSet.getInt(3));
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), specialityQuery.selectObject(speciality));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void insert(Employee object) {
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("""
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
    public void update(Employee object) {

    }
}

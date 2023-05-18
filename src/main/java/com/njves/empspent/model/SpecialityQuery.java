package com.njves.empspent.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityQuery extends Query<Speciality>{
    @Override
    public List<Speciality> select() {
        List<Speciality> specialities = new ArrayList<>();
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM speciality");
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                double salary = resultSet.getDouble(3);
                specialities.add(new Speciality(id, title, salary));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialities;
    }

    @Override
    public List<Speciality> select(Speciality param) {
        return null;
    }

    @Override
    public Speciality selectObject(Speciality param) {
        Speciality speciality = null;
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM speciality WHERE id = " + param.getId());
            while (resultSet.next())
                speciality = new Speciality(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return speciality;
    }

    @Override
    public void insert(Speciality object) {
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("""
            INSERT INTO speciality(title, salary) VALUES(?, ?)
            """);

            statement.setString(1, object.getTitle());
            statement.setDouble(2, object.getSalary());
            statement.executeUpdate();
            statement = database.getConnection().prepareStatement("""
                INSERT INTO required_spec(spec_id, emp_capacity) VALUES((SELECT id FROM speciality WHERE title = ?), 0)
            """);
            statement.setString(1, object.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Speciality object) {

    }
}

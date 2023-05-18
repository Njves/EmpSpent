package com.njves.empspent.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequiredSpecialityQuery extends Query<RequiredSpeciality> {
    @Override
    public List<RequiredSpeciality> select() {
        Query<Speciality> specialityQuery = new SpecialityQuery();
        List<RequiredSpeciality> specialities = new ArrayList<>();
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT s.id, rs.emp_capacity FROM required_spec as rs JOIN speciality as s ON rs.spec_id = s.id");
            while(resultSet.next()) {
                Speciality param = new Speciality();
                param.setId(resultSet.getInt(1));
                Speciality speciality = specialityQuery.selectObject(param);
                int empCapacity = resultSet.getInt(2);
                specialities.add(new RequiredSpeciality(speciality, empCapacity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialities;
    }

    @Override
    public List<RequiredSpeciality> select(RequiredSpeciality param) {
        return null;
    }

    @Override
    public RequiredSpeciality selectObject(RequiredSpeciality param) {
        return null;
    }

    @Override
    public void insert(RequiredSpeciality object) {
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("""
                INSERT INTO required_spec(spec_id, emp_capacity) VALUES (?, ?)
                """);
            statement.setInt(1, object.getSpeciality().getId());
            statement.setInt(2, object.getEmployeesCapacity());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Добавил новую требуемую специальность: " + object);
    }

    @Override
    public void update(RequiredSpeciality object) {
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("""
            UPDATE required_spec SET emp_capacity = ? WHERE spec_id = ?
            """);

            statement.setInt(1, object.getEmployeesCapacity());
            statement.setInt(2, object.getSpeciality().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Обновил empCapacity: " + object);
    }
}

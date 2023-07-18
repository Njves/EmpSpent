/**
 * Содержит команду запроса сотрудников
 */
package com.njves.empspent.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * команда запроса рабочих дней
 */
class WorkDayQuery extends Query<WorkDay> {
    /**
     * Делает выборку всех объектов рабочих дней
     * @return все объекты из выборки
     */
    @Override
    public List<WorkDay> select() {
        Query<Employee> employeeQuery = new EmployeeQuery();
        List<WorkDay> workDays = new ArrayList<>();
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM working_days");
            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(2));
                workDays.add(new WorkDay(resultSet.getInt(1), employeeQuery.selectObject(employee), LocalDate.parse(resultSet.getString(3)), resultSet.getBoolean(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workDays;
    }

    /**
     * Выборка всех объектов с параметрами
     * @param param параметры
     * @return выборка объектов
     */
    @Override
    public List<WorkDay> select(WorkDay param) {
        return null;
    }

    /**
     * Возвращает конкретный объект сотрудника
     * @param param параметр
     * @return возвращает объект
     */
    @Override
    public WorkDay selectObject(WorkDay param) {
        return null;
    }

    /**
     * Добавляет объект сотрудника в бд
     * @param object добавляемый объект
     */
    @Override
    public void insert(WorkDay object) {
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("""
            INSERT INTO working_days(emp_id, work_day, is_work) VALUES (?, ?, ?)
            """);
            statement.setInt(1, object.getEmployee().getId());
            statement.setString(2, object.getDate().toString());
            statement.setBoolean(3, object.isWorked());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Добавил новую отметку рабочего дня: " + object);
    }

    /**
     * Обновляет объект в базе данных
     * @param object обновляемый объект
     */
    @Override
    public void update(WorkDay object) {

    }
}

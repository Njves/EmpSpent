/**
 * Модуль содержащий адаптер запроса рабочих дней
 */
package com.njves.empspent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Декоратор запроса рабочих дней
 */
public class WorkDayQueryDecorator extends WorkDayQuery implements SelectableByDateQuery<WorkDay> {
    /**
     * Возвращает рабочие дни по интервалу месяцев
     * @param monthStart начальный месяц
     * @param monthEnd конечный месяц
     * @return выборка
     */
    @Override
    public List<WorkDay> getByMonthInterval(int monthStart, int monthEnd) {
        Query<Employee> employeeQuery = new EmployeeQuery();
        List<WorkDay> workDays = new ArrayList<>();
        try {

            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM working_days WHERE strftime('%m', work_day) IN (" + intervalToIn(monthStart, monthEnd) + ")");
            System.out.println("SELECT * FROM working_days WHERE strftime('%m', work_day) IN (" + intervalToIn(monthStart, monthEnd) + ")");
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
     * Возвращает рабочие дни по интервалу годов
     * @param monthStart начальный год
     * @param monthEnd конечный год
     * @return выборка
     */
    @Override
    public List<WorkDay> getByYearInterval(int monthStart, int monthEnd) {
        Query<Employee> employeeQuery = new EmployeeQuery();
        List<WorkDay> workDays = new ArrayList<>();
        try {
            ResultSet resultSet = database.getConnection().createStatement().executeQuery("SELECT * FROM working_days WHERE '" + transformIntToSql(monthStart) + "' <= strftime('%Y', work_day) <= '" + transformIntToSql(monthEnd) + "'");
            System.out.println("SELECT * FROM working_days WHERE '" + transformIntToSql(monthStart) + "' <= strftime('%Y', work_day) <= '" + transformIntToSql(monthEnd) + "'");
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
     * Переводит строку типа 1, 2, 3 в 01, 02, 03
     * @param number число
     * @return строка типа 01, 02, 03
     */
    private String transformIntToSql(int number) {
        String strNumber;
        if(number < 10) {
            strNumber = "0" + number;
            return strNumber;
        }
        return String.valueOf(number);
    }

    /**
     * Переводит интервал строку типа IN('01', '02', '03')
     * @param start начало интервала
     * @param end конец интервала
     * @return строка типа IN('01', '02', '03')
     */
    private String intervalToIn(int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            builder.append("'");
            builder.append(transformIntToSql(i));
            builder.append("'");
            if(i != end)
                builder.append(",");
        }
        return builder.toString();
    }
}

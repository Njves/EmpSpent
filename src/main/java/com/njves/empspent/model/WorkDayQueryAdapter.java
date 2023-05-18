package com.njves.empspent.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkDayQueryAdapter extends WorkDayQuery implements SelectableByDate<WorkDay> {
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

    private String transformIntToSql(int number) {
        String strNumber;
        if(number < 10) {
            strNumber = "0" + String.valueOf(number);
            return strNumber;
        }
        return String.valueOf(number);
    }
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

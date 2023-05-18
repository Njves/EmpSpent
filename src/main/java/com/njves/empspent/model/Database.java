package com.njves.empspent.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;
    private static final String empTable = "employee";
    private static final String specTable = "speciality";
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

    public void insertSpeciality(Speciality speciality) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
            INSERT INTO speciality(title, salary) VALUES(?, ?)
            """);

            statement.setString(1, speciality.getTitle());
            statement.setDouble(2, speciality.getSalary());
            statement.executeUpdate();
            statement = connection.prepareStatement("""
                INSERT INTO required_spec(spec_id, emp_capacity) VALUES((SELECT id FROM speciality WHERE title = ?), 0)
            """);
            statement.setString(1, speciality.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertEmployee(Employee employee) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
            INSERT INTO employee(name, speciality) VALUES(?, ?)
            """);

            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Добавил нового пользователя: " + employee);
    }

    public List<Speciality> getSpecialities() {
        List<Speciality> specialities = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM speciality");
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

    public Speciality getSpeciality(int id) {
        Speciality speciality = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM speciality WHERE id = " + id);
            while (resultSet.next())
                speciality = new Speciality(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return speciality;
    }

    public Employee getEmployee(int id) {
        Employee employee = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM employee WHERE id = " + id);
            while (resultSet.next())
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), getSpeciality(resultSet.getInt(3)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }


    public List<RequiredSpeciality> getRequiredSpeciality() {
        List<RequiredSpeciality> specialities = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT s.id, rs.emp_capacity FROM required_spec as rs JOIN speciality as s ON rs.spec_id = s.id");
            while(resultSet.next()) {
                Speciality speciality = getSpeciality(resultSet.getInt(1));
                int empCapacity = resultSet.getInt(2);
                specialities.add(new RequiredSpeciality(speciality, empCapacity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialities;
    }

    public void updateRequirementSpecialityEmployeeCapacity(RequiredSpeciality speciality) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
            UPDATE required_spec SET emp_capacity = ? WHERE spec_id = ?
            """);

            statement.setInt(1, speciality.getEmployeesCapacity());
            statement.setInt(2, speciality.getSpeciality().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Обновил empCapacity: " + speciality);
    }

    public void addRequirementSpeciality(RequiredSpeciality speciality) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("""
            INSERT INTO required_spec(spec_id, emp_capacity) VALUES (?, ?)
            """);
        statement.setInt(1, speciality.getSpeciality().getId());
        statement.setInt(2, speciality.getEmployeesCapacity());
        statement.executeUpdate();

        System.out.println("Добавил новую требуемую специальность: " + speciality);
    }

    public void addWorkDay(WorkDay workDay) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("""
            INSERT INTO working_days(emp_id, work_day, is_work) VALUES (?, ?, ?)
            """);
            statement.setInt(1, workDay.getEmployee().getId());
            statement.setString(2, workDay.getDate().toString());
            statement.setBoolean(3, workDay.isWorked());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Добавил новую отметку рабочего дня: " + workDay);
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM employee");
            while(resultSet.next()) {
                employees.add(new Employee(resultSet.getInt(1), resultSet.getString(2), getSpeciality(resultSet.getInt(3))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public List<WorkDay> getWorkingDays() {
        List<WorkDay> workDays = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM working_days");
            while(resultSet.next()) {
                workDays.add(new WorkDay(resultSet.getInt(1), getEmployee(resultSet.getInt(2)), LocalDate.parse(resultSet.getString(3)), resultSet.getBoolean(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workDays;
    }

    public List<WorkDay> getWorkingDays(int month) {
        List<WorkDay> workDays = new ArrayList<>();
        try {
            String strMonth = "0" + month;
            if (month >= 10) {
                strMonth = String.valueOf(month);
            }
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM working_days WHERE strftime('%m', work_day) = '" + strMonth + "'");
            System.out.println("SELECT * FROM working_days WHERE strftime('%m', work_day) = '" + strMonth + "'");
            while(resultSet.next()) {
                workDays.add(new WorkDay(resultSet.getInt(1), getEmployee(resultSet.getInt(2)), LocalDate.parse(resultSet.getString(3)), resultSet.getBoolean(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workDays;
    }
}

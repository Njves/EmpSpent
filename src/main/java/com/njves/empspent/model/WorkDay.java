package com.njves.empspent.model;

import java.time.LocalDate;

public class WorkDay {
    private int id;
    private Employee employee;
    private LocalDate date;

    private boolean isWorked;

    public WorkDay(int id, Employee employee, LocalDate date, boolean isWorked) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.isWorked = isWorked;
    }

    public WorkDay(Employee employee, LocalDate date, boolean isWorked) {
        this.employee = employee;
        this.date = date;
        this.isWorked = isWorked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isWorked() {
        return isWorked;
    }

    public void setWorked(boolean worked) {
        isWorked = worked;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", employee=" + employee +
                ", date=" + date +
                ", isWorked=" + isWorked +
                '}';
    }
}

package com.njves.empspent.model;

import java.util.Objects;

public class Speciality {
    private int id;
    private String title;
    private Double salary;

    public Speciality(int id, String title, Double salary) {
        this.id = id;
        this.title = title;
        this.salary = salary;
    }

    public Speciality(String title, Double salary) {
        this.title = title;
        this.salary = salary;
    }

    public Speciality() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, salary);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                '}';
    }
}

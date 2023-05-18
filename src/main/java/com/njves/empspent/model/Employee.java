package com.njves.empspent.model;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private Speciality speciality;

    public Employee(int id, String name, Speciality speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }
    public Employee(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(speciality, employee.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, speciality);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality=" + speciality +
                '}';
    }
}

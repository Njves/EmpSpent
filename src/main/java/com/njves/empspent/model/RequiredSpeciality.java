package com.njves.empspent.model;

import com.njves.empspent.controler.ItemListFormat;

public class RequiredSpeciality implements ItemListFormat {
    private int id;
    private Speciality speciality;
    private int employeesCapacity;

    public RequiredSpeciality(Speciality speciality, int employeesCapacity) {
        this.speciality = speciality;
        this.employeesCapacity = employeesCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public int getEmployeesCapacity() {
        return employeesCapacity;
    }

    public void setEmployeesCapacity(int employeesCapacity) {
        this.employeesCapacity = employeesCapacity;
    }

    @Override
    public String toString() {
        return "RequiredSpeciality{" +
                "id=" + id +
                ", speciality=" + speciality +
                ", employeesCapacity=" + employeesCapacity +
                '}';
    }

    @Override
    public String getItemText() {
        return "Для специалазации '" + speciality.getTitle() + "' требуется " + employeesCapacity + " человек";
    }
}

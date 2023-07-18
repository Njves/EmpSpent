/**
 * Содержит класс сотрудника
 */
package com.njves.empspent.model;

import java.util.Objects;

/**
 * Модель сотрудника
 */
public class Employee {
    /**
     * Идентификатор сотрудника
     */
    private int id;

    /**
     * Имя сотрудника
     */
    private String name;

    /**
     * Специальность сотрудника
     */
    private Speciality speciality;

    /**
     * Конструктор объекта
     * @param id идентификатор
     * @param name имя
     * @param speciality специальность
     */
    public Employee(int id, String name, Speciality speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    /**
     * Конструктор объекта без идентификатора
     * @param name имя
     * @param speciality специальность
     */
    public Employee(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    /**
     * Пустой конструктор
     */
    public Employee() {
    }

    /**
     * Возвращает идентификатор
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * задает идентификатор
     * @param id идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает имя сотрудника
     * @return имя сотрудника
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает специальность
     * @return специальность
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    /**
     * Сравнивает два объекта между собой
     * @param o объект
     * @return равны ли объекты
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(speciality, employee.speciality);
    }

    /**
     * Возвращает хэш код объекта
     * @return хэш код
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, speciality);
    }

    /**
     * Переводит объект в строку
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality=" + speciality +
                '}';
    }
}

/**
 * Модуль содержащий модель должности
 */
package com.njves.empspent.model;

import java.util.Objects;

/**
 * Модель должности
 */
public class Speciality {
    /**
     * Идентификатор должности
     */
    private int id;

    /**
     * Название должности
     */
    private String title;

    /**
     * Зарплата
     */
    private Double salary;

    /**
     * Параметризированный конструктор
     * @param id идентификатор
     * @param title название
     * @param salary зарплата
     */
    public Speciality(int id, String title, Double salary) {
        this.id = id;
        this.title = title;
        this.salary = salary;
    }

    /**
     * Параметризированный конструктор
     * @param title название
     * @param salary зарплата
     */
    public Speciality(String title, Double salary) {
        this.title = title;
        this.salary = salary;
    }

    /**
     * Пустой конструктор
     */
    public Speciality() {

    }

    /**
     * Возвращает идентификатор должности
     * @return идентификатор должности
     */
    public int getId() {
        return id;
    }

    /**
     * Фиксирует
     * @param id идентификатор должности
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает название должности
     * @return название должности
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возвращает зарплату по должности
     * @return зарплата по должности
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Сравнивает объекты между собой
     * @param o сравниваемый объект
     * @return равны ли объекты
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(salary, that.salary);
    }

    /**
     * Возвращает хэш код
     * @return хэш код
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, salary);
    }

    /**
     * Возвращает строковое представление объекта
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                '}';
    }
}

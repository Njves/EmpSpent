/**
 * Модуль содеражщий модель рабочего дня
 */
package com.njves.empspent.model;

import java.time.LocalDate;

/**
 * Модель рабочего дня
 */
public class WorkDay {
    /**
     * Идентификатор рабочего дня
     */
    private int id;

    /**
     * объект сотрудника
     */
    private final Employee employee;

    /**
     * Дата отработки
     */
    private final LocalDate date;

    /**
     * Отработан ли день
     */
    private boolean isWorked;

    /**
     * Параметризированный компьютер
     * @param id идентификатор
     * @param employee объект сотрудника
     * @param date дата
     * @param isWorked отработан ли день
     */
    public WorkDay(int id, Employee employee, LocalDate date, boolean isWorked) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.isWorked = isWorked;
    }

    /**
     * Параметризированный компьютер
     * @param employee объект сотрудника
     * @param date дата
     * @param isWorked отработан ли день
     */
    public WorkDay(Employee employee, LocalDate date, boolean isWorked) {
        this.employee = employee;
        this.date = date;
        this.isWorked = isWorked;
    }

    /**
     * Возвращает идентификатор рабочего дня
     * @return идентификатор рабочего дня
     */
    public int getId() {
        return id;
    }

    /**
     * Задает идентификатор рабочего дня
     * @param id идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает объект сотрудника
     * @return объект сотрудника
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Возвращает объект даты
     * @return объект даты
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Отработан ли день
     * @return отработан ли день
     */
    public boolean isWorked() {
        return isWorked;
    }

    /**
     * Возвращает текстовое представление объекта
     * @return текстовое представление объекта
     */
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

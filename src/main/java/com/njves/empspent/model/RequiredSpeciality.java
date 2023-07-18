/**
 * Модуль содержащий модель требуемой специальности
 */
package com.njves.empspent.model;

import com.njves.empspent.controler.ItemListFormat;

/**
 * Модель требуемой специальности
 */
public class RequiredSpeciality implements ItemListFormat {
    /**
     * Идентификатор модели
     */
    private int id;

    /**
     * Объект специальности
     */
    private Speciality speciality;

    /**
     * Количество требуемых сотрудников
     */
    private int employeesCapacity;

    /**
     * Параметризированный конструток
     * @param speciality специальность
     * @param employeesCapacity количество сотрудников
     */
    public RequiredSpeciality(Speciality speciality, int employeesCapacity) {
        this.speciality = speciality;
        this.employeesCapacity = employeesCapacity;
    }

    /**
     * Возвращает идентификатор
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Задает идентификатор
     * @param id идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает специальность
     * @return объект специальности
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    /**
     * Возвращает количество требуемых сотрудников
     * @return количество требуемых сотрудников
     */
    public int getEmployeesCapacity() {
        return employeesCapacity;
    }

    /**
     * Задает количество требуемых сотрудников
     * @param employeesCapacity количество требуемых сотрудников
     */
    public void setEmployeesCapacity(int employeesCapacity) {
        this.employeesCapacity = employeesCapacity;
    }

    /**
     * Возвращает текстовое представление объекта
     * @return текстовое представление объекта
     */
    @Override
    public String toString() {
        return "RequiredSpeciality{" +
                "id=" + id +
                ", speciality=" + speciality +
                ", employeesCapacity=" + employeesCapacity +
                '}';
    }

    /**
     * Возвращает текстовое представления для элемента списка
     * @return текстовое представления для элемента списка
     */
    @Override
    public String getItemText() {
        return "Для специалазации '" + speciality.getTitle() + "' требуется " + employeesCapacity + " человек";
    }
}

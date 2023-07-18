/**
 * Модуль содержащий Интерфейс выборки по датам
 */
package com.njves.empspent.model;

import java.util.List;

/**
 * Интерфейс выборки по датам
 * @param <T> тип выборки
 */
public interface SelectableByDateQuery<T> {
    /**
     * Выборка по интервалу месяцев
     * @param monthStart начало интервала
     * @param monthEnd конец интервалов
     * @return выборка по месяцам
     */
    List<T> getByMonthInterval(int monthStart, int monthEnd);

    /**
     * Выборка по интервалу годов
     * @param monthStart начало интервала
     * @param monthEnd конец интервалов
     * @return выборка по годам
     */
    List<T> getByYearInterval(int monthStart, int monthEnd);
}

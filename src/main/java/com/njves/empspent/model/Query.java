/**
 * Содержит команду запроса
 */
package com.njves.empspent.model;

import java.util.List;

/**
 * Команда запроса
 * @param <T> тип запрашеваемого
 */
public abstract class Query<T> {
    /**
     * Объект базы данных
     */
    protected Database database = Database.getInstance();

    /**
     * Делает выборку всех объектов
     * @return все объекты из выборки
     */
    public abstract List<T> select();

    /**
     * Выборка всех объектов с параметрами
     * @param param параметры
     * @return выборка объектов
     */
    public abstract List<T> select(T param);

    /**
     * Возвращает конкретный объект
     * @param param параметр
     * @return возвращает объект
     */
    public abstract T selectObject(T param);

    /**
     * Добавляет объект в бд
     * @param object добавляемый объект
     */
    public abstract void insert(T object);

    /**
     * Обновляет объект в базе данных
     * @param object обновляемый объект
     */
    public abstract void update(T object);

}

/**
 * Модуль содержащий интерфейс реализующий возможность
 * открывать дочерние окна контроллером
 */
package com.njves.empspent.app;

import java.util.HashMap;

/**
 * Интерфейс реализующий возможность открывать дочерние окна контроллером
 */
public interface OpenableWindow {

    /**
     * Назначает окну аргументы
     * @param args аргументы
     * @param keys ключи
     */
    void setArguments(HashMap<String, Object> args, String[] keys);
}

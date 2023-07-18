/**
 * Модуль содержащий фабрику ячеек
 */
package com.njves.empspent.controler;

import com.njves.empspent.model.WorkDay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Фабрика ячеек списка
 */
public class WorkDayCellFactory implements Callback<ListView<WorkDay>, ListCell<WorkDay>> {
    /**
     * при запросле ячейки списка создает объект ячейки
     * @param workDayListView объект списка
     * @return объект ячейки
     */
    @Override
    public ListCell<WorkDay> call(ListView<WorkDay> workDayListView) {
        return new WorkDayCell();
    }
}

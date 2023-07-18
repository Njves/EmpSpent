/**
 * Модуль содеражащий класс ячейки списка
 */
package com.njves.empspent.controler;

import com.njves.empspent.Application;
import com.njves.empspent.model.WorkDay;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

/**
 * Ячейка списка рабочего дня
 */
public class WorkDayCell extends ListCell<WorkDay> {
    /**
     * лейбл даты
     */
    public Label labelDate;

    /**
     * лейбл имена сотрудника
     */
    public Label labelEmployee;

    /**
     * чек бокс отработал ли человек
     */
    public CheckBox checkBoxIsWorked;

    /**
     * лейбл названия специализации
     */
    public Label labelSpecialization;


    /**
     * загружает верстку
     */
    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("work-cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * вызывается про заполнение ячейки списка
     * @param workDay рабочий день
     * @param b пустая ли ячейка
     */
    @Override
    protected void updateItem(WorkDay workDay, boolean b) {
        super.updateItem(workDay, b);

        if(b || workDay == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
            return;
        }
        labelDate.setText(workDay.getDate().toString());
        labelEmployee.setText(workDay.getEmployee().getName());
        labelSpecialization.setText(workDay.getEmployee().getSpeciality().getTitle());
        checkBoxIsWorked.setSelected(workDay.isWorked());
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

    }

    /**
     * пустой конструктор
     */
    public WorkDayCell() {
        loadFxml();
    }
}

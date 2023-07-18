/**
 * Модуль содержащий контроллер окна добавления рабочего окна
 */
package com.njves.empspent.controler;

import com.njves.empspent.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Контроллер окна добавления рабочего дня
 */
public class AddWorkDayController implements Initializable {
    /**
     * элемент выбора даты раоты
     */
    public DatePicker datePicker;

    /**
     * выбор сотрудника
     */
    public ChoiceBox<Employee> choiceBox;

    /**
     * кнопка добавления рабочего дня
     */
    public Button buttonAdd;

    /**
     * чек бокс отработки дня
     */
    public CheckBox checkButton;

    List<Employee> employees;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Employee> employeeQuery = new EmployeeQuery();
        Query<WorkDay> workDayQuery = new WorkDayQueryDecorator();
        employees = employeeQuery.select();
        choiceBox.setItems(FXCollections.observableList(employees));
        choiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Employee object) {
                if(object == null)
                    return "";
                return object.getName();
            }

            @Override
            public Employee fromString(String string) {
                return employees.stream().filter(employee -> employee.getName().equals(string)).findFirst().get();
            }
        });
        buttonAdd.setOnMouseClicked(event -> {
            if(!isValid())
                return;

            LocalDate localDate = datePicker.getValue();
            workDayQuery.insert(new WorkDay(choiceBox.getValue(), localDate, checkButton.isSelected()));
        });
    }

    private boolean isValid() {
        return choiceBox.getValue() != null && datePicker.getValue() != null;
    }
}

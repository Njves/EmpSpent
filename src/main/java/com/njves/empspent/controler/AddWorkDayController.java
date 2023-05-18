package com.njves.empspent.controler;

import com.njves.empspent.model.Database;
import com.njves.empspent.model.Employee;
import com.njves.empspent.model.WorkDay;
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

public class AddWorkDayController implements Initializable {
    public DatePicker datePicker;
    public ChoiceBox<Employee> choiceBox;
    public Button buttonAdd;
    public CheckBox checkButton;

    List<Employee> employees;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employees = Database.getInstance().getEmployees();
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
            Database.getInstance().addWorkDay(new WorkDay(choiceBox.getValue(), localDate, checkButton.isSelected()));
        });
    }

    private boolean isValid() {
        return choiceBox.getValue() != null && datePicker.getValue() != null;
    }
}

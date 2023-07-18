/**
 * Модуль содеражщий контроллер окна добавлящий сотрудников
 */

package com.njves.empspent.controler;

import com.njves.empspent.app.Toast;
import com.njves.empspent.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Контроллер окна добавляющий сотрудников
 */
public class AddEmployeeController implements Initializable {
    /**
     * поле ввода имени
     */
    public TextField textFieldName;

    /**
     * поле ввода фамилии
     */
    public TextField textFieldLastName;

    /**
     * кнопка выбора специальности сотрудника
     */
    public MenuButton menuSpeciality;

    /**
     * кнопка добавления сотрудника
     */
    public Button addButton;

    /**
     * Вызывается при инициализации окна
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<String, Speciality> map = new HashMap<>();
        Query<Employee> employeeQuery = new EmployeeQuery();
        Query<Speciality> specialityQuery = new SpecialityQuery();
        System.out.println(specialityQuery.select());
        for (Speciality speciality : specialityQuery.select()) {
            map.put(speciality.getTitle(), speciality);
            MenuItem item = new MenuItem(speciality.getTitle());
            item.setOnAction((event -> menuSpeciality.setText(item.getText())));
            menuSpeciality.getItems().add(item);
            menuSpeciality.setText(speciality.getTitle());
        }

        addButton.setOnMouseClicked((event -> {
            if(!isValid()) {
                Toast.makeText((Stage) textFieldName.getScene().getWindow(), "Добавил сотрудника");
            }
            String builder = textFieldName.getText().trim() +
                    " " +
                    textFieldLastName.getText().trim();
            System.out.println(menuSpeciality.getText());
            employeeQuery.insert(new Employee(builder, map.get(menuSpeciality.getText())));
        }));
    }

    /**
     * валидирует поля текста
     * @return валидны ли поля
     */
    private boolean isValid() {
        return !textFieldName.getText().isEmpty() && !textFieldLastName.getText().isEmpty();
    }
}

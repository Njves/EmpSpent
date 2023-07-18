/**
 * Модуль окна добавляющий специальность
 */
package com.njves.empspent.controler;

import com.njves.empspent.model.Speciality;
import com.njves.empspent.model.SpecialityQuery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Контроллер окна добавляющий специальность
 */
public class AddSpecialityController implements Initializable {
    /**
     * Кнопка добавления специальности
     */
    public Button addButton;

    /**
     * Поле ввоада название специальности
     */
    public TextField textFieldTitle;

    /**
     * Поле ввода добавления зарплаты
     */
    public TextField textFieldSalary;


    /**
     * Вызывается при инициализации окна
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpecialityQuery specialityQuery = new SpecialityQuery();
        addButton.setOnMouseClicked((event -> {
            if(!validate()) return;
            specialityQuery.insert(new Speciality(textFieldTitle.getText().trim(),
                    Double.parseDouble(textFieldSalary.getText())));

        }));
    }

    /**
     * Функция валидации результата
     * @return валидный ли поля
     */
    private boolean validate() {
        boolean valid;
        valid = !textFieldTitle.getText().isEmpty();
        try {
            double salary = Double.parseDouble(textFieldSalary.getText());
        } catch (NumberFormatException e) {
            valid = false;
            System.out.println("Что то плохое");
        }
        return valid;
    }
}

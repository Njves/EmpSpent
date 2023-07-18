/**
 * Модуль содеражщий контроллер окна добавлящий требуемых сотрудников
 */
package com.njves.empspent.controler;

import com.njves.empspent.app.CustomController;
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
 * Контроллер диалогового окна добавляющий требуемых сотрудников
 */
public class AddRequiredSpecialityController extends CustomController implements Initializable {
    /**
     * кнопка выбора специальности
     */
    public MenuButton menuButtonSpeciality;

    /**
     * полле ввода количество сотрудников
     */
    public TextField textFieldEmpCapacity;

    /**
     * кнопка добавления требуемой специальности
     */
    public Button buttonAdd;

    /**
     * Команда запроса требуемых специальностей
     */
    private final Query<RequiredSpeciality> query = new RequiredSpecialityQuery();

    /**
     * Задает аргументы диалоговому окну
     * @param args аргументы
     * @param keys ключи
     */
    @Override
    public void setArguments(HashMap<String, Object> args, String[] keys) {

    }

    /**
     * Вызывается при инициализации окна
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpecialityQuery specialityQuery = new SpecialityQuery();
        HashMap<String, Speciality> map = new HashMap<>();
        for (Speciality speciality : specialityQuery.select()) {
            map.put(speciality.getTitle(), speciality);
            MenuItem item = new MenuItem(speciality.getTitle());
            item.setOnAction((event -> menuButtonSpeciality.setText(item.getText())));
            menuButtonSpeciality.getItems().add(item);
            menuButtonSpeciality.setText(speciality.getTitle());
        }

        buttonAdd.setOnMouseClicked(event -> {
            query.insert(new RequiredSpeciality(map.get(menuButtonSpeciality.getText()),
                    Integer.parseInt(textFieldEmpCapacity.getText())));

            ((Stage) (textFieldEmpCapacity.getScene().getWindow())).close();
        });
    }


}

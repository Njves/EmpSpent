/**
 * Модуль обновления требуемой специальности
 */
package com.njves.empspent.controler;

import com.njves.empspent.app.CustomController;
import com.njves.empspent.model.Query;
import com.njves.empspent.model.RequiredSpeciality;
import com.njves.empspent.model.RequiredSpecialityQuery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Контроллер обновления требуемого сотрудника
 */
public class UpdateRequiredSpecialityController extends CustomController implements Initializable {
    /**
     * текстовое поле количества сотрдуников
     */
    public TextField textFieldEmpCapacity;

    /**
     * кнопка обновления
     */
    public Button buttonUpdate;

    /**
     * текущий обновляемый объект
     */
    public RequiredSpeciality requiredSpeciality;

    /**
     * объект запроса
     */
    private final Query<RequiredSpeciality> query = new RequiredSpecialityQuery();

    /**
     * вызывается при инициализации
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonUpdate.setOnMouseClicked((event -> {
            requiredSpeciality.setEmployeesCapacity(Integer.parseInt(textFieldEmpCapacity.getText()));
            query.update(requiredSpeciality);
            ((Stage) (textFieldEmpCapacity.getScene().getWindow())).close();
        }));
    }

    /**
     * вызывается при инициализации из родительского контроллера
     * @param requiredSpeciality передаваемый объект
     */
    public void setRequiredSpeciality(RequiredSpeciality requiredSpeciality) {
        this.requiredSpeciality = requiredSpeciality;
        textFieldEmpCapacity.setText(String.valueOf(requiredSpeciality.getEmployeesCapacity()));
    }

    /**
     * Задает объекты в контроллере
     * @param args аргументы
     * @param keys ключи
     */
    @Override
    public void setArguments(HashMap<String, Object> args, String[] keys) {
        setRequiredSpeciality((RequiredSpeciality) args.get(keys[0]));
    }
}

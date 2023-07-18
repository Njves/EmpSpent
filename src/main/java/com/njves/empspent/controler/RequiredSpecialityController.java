/**
 * Модуль содеражший контроллер вывода требуемых сотрудников
 */
package com.njves.empspent.controler;

import com.njves.empspent.app.WindowFactory;
import com.njves.empspent.model.Query;
import com.njves.empspent.model.RequiredSpeciality;
import com.njves.empspent.model.RequiredSpecialityQuery;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Контроллер окна вывода требуемых сотрудников
 */
public class RequiredSpecialityController implements Initializable {
    /**
     * Список требуемых сотрудников
     */
    public ListView<RequiredSpeciality> listView;

    /**
     * Кнопка добавления сотрудника
     */
    public Button buttonAdd;

    /**
     * Фабрика создания окна
     */
    private final WindowFactory factory = new WindowFactory();

    /**
     * Запрос требуемых сотрудников
     */
    private final Query<RequiredSpeciality> query = new RequiredSpecialityQuery();

    /**
     * функция инициализации
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateList();

        listView.setOnMouseClicked((event -> {
            RequiredSpeciality requiredSpeciality = null;
            if(listView.getSelectionModel().getSelectedItems().size() > 0) {
                requiredSpeciality = listView.getSelectionModel().getSelectedItems().get(0);
            }
            if(requiredSpeciality == null) {
                return;
            }
            HashMap<String, Object> map = new HashMap<>();
            String[] key = new String[]{"requiredSpeciality"};
            map.put(key[0], requiredSpeciality);
            factory.showWindow("upd-req", map, key, this::updateList);
        }));

        buttonAdd.setOnMouseClicked(event -> factory.showWindow("add-req",null, null, this::updateList));

    }

    /**
     * обновляет список
     */
    private void updateList() {
        listView.getItems().clear();
        List<RequiredSpeciality> list = query.select();
        list.forEach(requiredSpeciality -> listView.getItems().add(requiredSpeciality));
        listView.setCellFactory(param ->  new ListCell<>(){
            @Override
            protected void updateItem(RequiredSpeciality requiredSpeciality, boolean b) {
                super.updateItem(requiredSpeciality, b);
                setText(b ? null : requiredSpeciality.getItemText());
            }
        });

    }
}
